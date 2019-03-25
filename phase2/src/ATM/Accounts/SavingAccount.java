package ATM.Accounts;

import ATM.Accounts.ISaverPlans.ISaverPlan;

import java.util.ArrayList;

/**
 * A class that represent a saving account
 */
public class SavingAccount extends AssetAccount {

    /** The saving plan for this account*/
    private ISaverPlan iSaverPlan;

    /** The account number */
    private final String accountNum;

    /**
     * Constructor of saving account
     * Create a new saving account with ownerID, ISaverPlan and the total number of accounts created
     *
     * @param ownerID the ID of the owner
     * @param plan the plan which result in the value of interest
     * @param totalNumAcc the total number of accounts created
     */
    public SavingAccount(ArrayList<String> ownerID, ISaverPlan plan, int totalNumAcc){
        super(ownerID);
        this.iSaverPlan = plan;
        this.accountNum = "004" +  (totalNumAcc + 1);
        setBalance(iSaverPlan.compute(getBalance()));
    }

    /**Getter method for available credit
     * @return available credit: the amount of money which can be retrieved from this account
     */
    @Override
    public double getAvailableCredit() {
        return getBalance();
    }

    /**Getter method for account number
     * @return account number
     */
    @Override
    public String getAccountNum(){
        return accountNum;
    }

    /**Setter method for saver plan */
    public void setSaverPlan(ISaverPlan s) {
        this.iSaverPlan = s;
    }

    /**
     * Return a String representation of this account
     * @return a string combined with account type, account number
     */
    @Override
    public String toString() {
        return ("Saving Account " + this.accountNum);
    }

    /**
     * Return the summary of this account
     * @return a String combined with account type, account number, balance
     */
    public String getSummary() {
        return (this.toString() + " , Remaining Balance: " + getBalance());
    }
}
