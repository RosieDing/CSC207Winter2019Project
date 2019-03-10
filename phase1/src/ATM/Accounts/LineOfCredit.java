package ATM.Accounts;

import ATM.InfoHandling.InfoManager;

/**
 * Line of credit account class
 */
public class LineOfCredit extends DebtAccount implements TransferOutable {
    private final String accountNum = "002" + getOwnerID() + (InfoManager.getInfoManager().getAccountNum() + 1);


    /**
     * Constructor of line of credit account
     * Create a new line of credit account with ownerID and limit
     *
     * @param ownerID the ID of the owner
     * @param limit the limit of this line of credit account
     */
    public LineOfCredit(String ownerID, double limit) {
        super(ownerID, limit);
    }

    /**Transfer out method for transfering money out of line of credit account */
    @Override
    public void transferOut(double amount){
        double new_balance = getBalance() + amount;
        setBalance(new_balance);
    }

    /**Getter for getting line of credit account number */
    public String getAccountNum(){
        return this.accountNum;
    }

    /**To string method */
    @Override
    public String toString() {
        return ("LineOfCredit" + ", "  + this.accountNum + ", " + getBalance());
    }

}