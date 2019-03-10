package ATM.Accounts;

import ATM.InfoHandling.InfoManager;

/**
 * Line of credit account class
 */
public class LineOfCredit extends DebtAccount implements TransferOutable {
    private String ownerID;
    private final String accountNum = "002" + ownerID + (InfoManager.getInfoManager().getAccountNum() + 1);


    /**Constructor for debt account class */
    public LineOfCredit(String ownerID, double limit) {
        super(ownerID, limit);
        this.ownerID = ownerID;
    }

    /**Transferout method for transfering money out of line of credit account */
    @Override
    public void transferOut(double amount){
        double newbalance = getBalance() + amount;
        setBalance(newbalance);
    }

    /**Getter for getting line of credit account number*/
    public String getAccountNum(){
        return this.accountNum;
    }

    /**To string method*/
    @Override
    public String toString() {
        return ("LineOfCredit" + ", "  + this.accountNum + ", " + getBalance());
    }

}