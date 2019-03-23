package ATM.Accounts;


/**
 * Chequing account class
 */
public class ChequingAccount extends AssetAccount{
    private double overDraftLimit = 100;
    private final String accountNum;

    /**
     * Constructor of chequing account
     * Create a new chequing account with ownerID
     *
     * @param ownerID the ID of the owner
     * @param totalNumAcc the total number of accounts created
     */
    public ChequingAccount(String ownerID, int totalNumAcc){
        super(ownerID);
        this.accountNum = "003" + getOwnerID() + String.valueOf(totalNumAcc) + 1;
    }

    /**Setter method for setting over draft limit */
    public void setOverDraftLimit(double limit){
        this.overDraftLimit = limit;
    }

    /**Getter method for returning the over draft limit */
    public double getOverDraftLimit() {return overDraftLimit;
    }

    /**Getter method for getting the available credit of chequing account */
    @Override
    public double getAvailableCredit(){
        return (getBalance()+overDraftLimit);
    }

    /**Getter method for getting the account number of chequing account */
    @Override
    public String getAccountNum(){
        return accountNum;
    }

    /**@string a string combined with account type, account number */
    @Override
    public String toString() {
        return ("Chquing Account " + this.accountNum);
    }

    /** @return a String that includes the summary of chequing account: type, number, balance */
    public String getSummary() {
        return (this.toString() + " , Remaining Balance: " + getBalance());
    }

}
