package ATM.BankIdentities;

import ATM.Accounts.*;
import ATM.InfoHandling.InfoStorer;
import ATM.Machine.CashMachine;
import ATM.Machine.Money;
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
        Transaction e = InfoManager.getTransactionManager().getAccLastTrans(accNum).reverse();
        // try catch where pay bill can't be reversed.
        TransactionManager manager = InfoManager.getTransactionManager();
        manager.makeTransaction(e);
        // try catch if transaction cant be processed.
    }


    //code smell to be fixed.
    public int createNewAccount(int userID, String accountType) {
        User u = InfoManager.getUser(userID);
        UserAccManager m = u.getAccManager();
        int result = 0;
        Account acc;
        switch (accountType) {
            case "Chequing":
                acc = new ChequingAccount(userID);
                m.addAccount(acc);
                m.getTypeAccounts("ChequingAccount");
                if (m.getTypeAccounts("ATM.Accounts.ChequingAccount").size() == 0) {
                    acc.setPrimary(true);
                }
                result = acc.getAccountNum();
                break;
            case "Line of Credit":
                int limit;
                //scanner get limit
                acc = new LineOfCredit(userID, limit);
                m.addAccount(acc);
                result = acc.getAccountNum();
                break;
            case "Credit":
                int limit;
                //scanner get limit
                acc = new CreditAccount(userID, limit);
                m.addAccount(acc);
                result = acc.getAccountNum();
                break;
            case "Saving":
                acc = new SavingAccount(userID);
                m.addAccount(acc);
                result = acc.getAccountNum();
                break;
        }
        return result; // raise exception if result==0
    }

    public void createNewChequingAccount(int userID, String accountType) {
        User u = InfoManager.getUser(userID);
        UserAccManager m = u.getAccManager();
        Account acc;
        acc = new ChequingAccount(userID);
        if (m.getTypeAccounts("ChequingAccount").size() == 0){

        }
        m.addAccount(acc);


    }
}
}
