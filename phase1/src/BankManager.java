import java.util.Random;

public class BankManager extends BankIdentity {

    public BankManager() {
        String id = "";
        id = id + "510" + (Loader.getBankManagerNum() + 1);
        this.setId(Integer.valueOf(id));
    }

    public void createUser(){
        User u = new User();
        Random r = new Random();
        int randomPass = r.nextInt(100000000);
        UserAccManager accM = new UserAccManager(u.getId());
        u.setAccManager(accM);
        PasswordManager passM = new PasswordManager(u.getId());
        passM.setPassword(String.valueOf(randomPass));
        u.setPassManager(passM);
        System.out.println("New user created! user ID: " + u.getId()
                + " initial Password: " + randomPass);
    }

    public void restock(CashMachine machine, Money money){
        machine.setAmount(money);
        // how to prevent other identities from touching cash machine setter?
    }

    public void undoMostRecentTrans(int accNum) {
        Transaction e = Loader.getAccount(accNum).getTransManager().getLastTrans().reverse();
        // try catch where pay bill can't be reversed.
        TransactionManager manager = Loader.getTransManager();
        manager.makeTransaction(e);
        // try catch if transaction cant be processed.
    }


    //code smell to be fixed.
    public int createNewAccount(int userID, String accountType) {
        User u = Loader.getUser(userID);
        UserAccManager m = u.getAccManager();
        int result = 0;
        Account acc;
        switch (accountType) {
            case "Chequing":
                acc = new ChequingAccount(userID);
                m.addAccount(acc);
                result = acc.getAccountNum();
                break;
            case "Line of Credit":
                int limit;
                //scanner get limit
                acc = new LineOfCredit(userID,limit);
                m.addAccount(acc);
                result = acc.getAccountNum();
                break;
            case "Credit":
                int limit;
                //scanner get limit
                acc = new CreditAccount(userID,limit);
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
}