package ATM.BankIdentities;

import ATM.Accounts.*;
import ATM.Machine.CashMachine;
import ATM.Machine.Money;
import ATM.Transactions.NoTransactionException;
import ATM.Transactions.ReverseNotPossibleException;
import ATM.Transactions.Transaction;
import ATM.Transactions.TransactionManager;
import ATM.InfoHandling.InfoManager;

import java.sql.SQLOutput;


/** BankManger class */
public class BankManager extends BankEmployee {
    private final String id;
    private PasswordManager manager;

    /** Creates a new BankManager with a password. Print its id and password out.
     *
     * @param password the password of the bankManager.
     * */
    public BankManager(String password) {
        this.id = "510" + (InfoManager.getInfoManager().getBankManagerNum() + 1);
        manager = new PasswordManager(this.id);
        manager.setPassword(password);
        manager.addObserver(InfoManager.getInfoManager());
        InfoManager.getInfoManager().add(this);
    }

    /** get the PassManager
     *
     * @return the password manager for manipulate the password
     * */
    public PasswordManager getPassManager(){
        return manager;
    }

    /** get the private ID of the manager
     *
     * @return the ID of the manager.
     *  */
    public String getId(){
        return id;
    }


    /**
     * The method for the BankManger to create new_user, user_accounts_Manager
     * with new accounts.
     * And BankManger could init the PassWord of the user with "1234".
     * */
    public void createUser() {
        AccountCreator creat = new AccountCreator();
        User u = new User();
        UserAccManager accM = new UserAccManager(u.getId());
        u.setAccManager(accM);
        PasswordManager passM = new PasswordManager(u.getId());
        passM.setPassword("1234");
        u.setPassManager(passM);
        System.out.println("New user created! user ID: " + u.getId()
                + " initial Password: " + "1234");
        InfoManager.getInfoManager().add(u);
        passM.addObserver(InfoManager.getInfoManager());
        creat.createNewChequingAccount(u.getId());


    }

    /** Restocking the CashMachine
     *
     * @param machine the CashMachine storing cash
     * @param money the money object
     * */
    public void restock(CashMachine machine, Money money) {
        machine.setAmount(money);
        // how to prevent other identities from touching cash machine setter?
    }

    /** Undo the most recent transaction for the Account
     *
     * @param accNum the AccountNumber of the account which you want to undo transaction for
     * @throws ReverseNotPossibleException
     *  */
    public void undoAccMostRecentTrans(String accNum) throws NoTransactionException {
        try {
            Transaction e = InfoManager.getInfoManager().getTransactionManager().popAccLastTrans(accNum).reverse();
            // try catch where pay bill can't be reversed.
            TransactionManager manager = InfoManager.getInfoManager().getTransactionManager();
            manager.makeTransaction(e);
            // try catch if transaction cant be processed.
        } catch (ReverseNotPossibleException e) {
            System.out.println("Impossible to undo this transaction.");
        }
    }


    /** Undo the most recent transaction for the user
     *
     * @param userId the UserId of the user which you want to undo transaction for
     * @throws ReverseNotPossibleException
     *  */
    public void undoUserMostRecentTrans(String userId) {
        try {
            Transaction e = InfoManager.getInfoManager().getTransactionManager().popUserLastTrans(userId).reverse();
            // try catch where pay bill can't be reversed.
            TransactionManager manager = InfoManager.getInfoManager().getTransactionManager();
            manager.makeTransaction(e);
            // try catch if transaction cant be processed.
        } catch (ReverseNotPossibleException e) {
            System.out.println("Impossible to undo this transaction.");
        } catch (NoTransactionException e) {
            System.out.println(e.getMessage());
        }
    }
}

