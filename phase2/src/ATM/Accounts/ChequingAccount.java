package ATM.Accounts;

import java.util.ArrayList;

/**
 * A class that represent a Chequing account
 */
public class ChequingAccount extends AssetAccount{

    /** The extra amount of money that the account balance is allowed to decrease under 0
     * In default, the balance is allowed to decrease to -100
     */
    private double overDraftLimit = 100;

    /** The account number */
    private final String accountNum;

    private final String chequing_code = "003";

    /**
     * Constructor of Chequing account
     * Create a new Chequing account with owner ID and total number of accounts created
     *
     * @param ownerID the ID of the owner
     * @param totalNumAcc the total number of accounts created
     */
    public ChequingAccount(ArrayList<String> ownerID, int totalNumAcc){
        super(ownerID);
        this.accountNum = chequing_code + (totalNumAcc + 1);
    }

    /**Setter method for setting over draft limit */
    public void setOverDraftLimit(double limit){
        this.overDraftLimit = limit;
    }

    /**Getter method for returning the over draft limit */
    public double getOverDraftLimit() { return overDraftLimit; }

    /**Getter method for getting the available credit of Chequing account
     * Available credit is the amount of money which can be retrieved from this account*/
    @Override
    public double getAvailableCredit(){
        return (getBalance() +overDraftLimit);
    }

    /**Getter method for getting the account number of chequing account */
    @Override
    public String getAccountNum(){
        return accountNum;
    }

    /**
     * Return a String representation of this account
     * @return a string combined with account type, account number
     */
    @Override
    public String toString() {
        return ("Chquing Account " + this.accountNum);
    }

    /**
     * Return the summary of this account
     * @return a String combined with account type, account number, balance
     */
    public String getSummary() {
        return (this.toString() + " , Remaining Balance: " + getBalance());
    }
}
