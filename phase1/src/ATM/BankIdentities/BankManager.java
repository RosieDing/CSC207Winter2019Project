package ATM.BankIdentities;

import ATM.Accounts.*;
import ATM.Machine.CashMachine;
import ATM.Machine.Money;
import ATM.Transactions.ReverseNotPossibleException;
import ATM.Transactions.Transaction;
import ATM.Transactions.TransactionManager;
import ATM.InfoHandling.InfoManager;



public class BankManager extends BankIdentity {

    public BankManager() {
        String id = "";
        id = id + "510" + (InfoManager.getBankManagerNum() + 1);
        this.setId(Integer.valueOf(id));
    }

    public void createUser() {
        /*
        need to add to the loader list.
         */
        User u = new User();
        UserAccManager accM = new UserAccManager(u.getId());
        u.setAccManager(accM);
        PasswordManager passM = new PasswordManager(u.getId());
        passM.setPassword("1234");
        u.setPassManager(passM);
        System.out.println("New user created! user ID: " + u.getId()
                + " initial Password: " + "1234");
    }

    public void restock(CashMachine machine, Money money) {
        machine.setAmount(money);
        // how to prevent other identities from touching cash machine setter?
    }

    /*
    May need to write the exception
     */
    public void undoMostRecentTrans(int accNum) {
        try {
            Transaction e = InfoManager.getTransactionManager().getAccLastTrans(accNum).reverse();
            // try catch where pay bill can't be reversed.
            TransactionManager manager = InfoManager.getTransactionManager();
            manager.makeTransaction(e);
            // try catch if transaction cant be processed.
        } catch (ReverseNotPossibleException e) {
            System.out.println("Impossible to undo this transaction.");;
        }
    }

    public void createNewChequingAccount(int userID) {
        User u = InfoManager.getUser(userID);
        UserAccManager m = u.getAccManager();
        ChequingAccount acc = new ChequingAccount(userID);
        if (m.getPrimaryChq() == null){
            try {
                m.setPrimaryChq(acc);
            } catch(AlreadyPrimaryException e ){
                System.out.println();
            }
        }
        m.addAccount(acc);

    }

    public void createNewSavingAccount(int userID){
        User u = InfoManager.getUser(userID);
        UserAccManager m = u.getAccManager();
        MonthlyInterest interest = new MonthlyInterest(0.01);
        SavingAccount acc = new SavingAccount(userID, interest);
        m.addAccount(acc);
    }

    public void createNewCreditAccount(int userID, double limit){
        User u = InfoManager.getUser(userID);
        UserAccManager m = u.getAccManager();
        CreditAccount acc = new CreditAccount(userID, limit);
        m.addAccount(acc);
    }

    public void createNewLineOfCreadit(int userID, double limit){
        User u = InfoManager.getUser(userID);
        UserAccManager m =u.getAccManager();
        LineOfCredit acc = new LineOfCredit(userID, limit);
        m.addAccount(acc);
    }
}

