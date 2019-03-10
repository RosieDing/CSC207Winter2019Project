package ATM.Accounts;


import ATM.BankIdentities.User;
import ATM.InfoHandling.InfoManager;

/**
 * Line of credit account class
 */
public class LineOfCredit extends DebtAccount implements TransferOutable {
    private int ownerID;
    private User owner= InfoManager.get(ownerID);
    private String id = "002" + ownerID + String.valueOf(owner.getAccountNum() + 1);
    private final int accountNum = Integer.valueOf(id);
    private double availableCredit = limit - balance;
    private double balance;

    /**Constructor for debt account class */
    public LineOfCredit(int ownerID, int limit) {
        super(ownerID, limit, availableCredit);
    }
    @Override

    /**Transferout method for transfering money out of line of credit account */
    public void transferOut(double amount){
        double newbalance = getBalance() + amount;
        setBalance(newbalance);
        setChanged();
        notifyObservers();
    }

    /**Getter for getting line of credit account number*/
    public int getAccountNum(){
        return this.accountNum;
    }

    /**To string method*/
    @Override
    public String toString() {
        return ("LineOfCredit" + ", "  + this.accountNum + ", " + getBalance());
    }

}