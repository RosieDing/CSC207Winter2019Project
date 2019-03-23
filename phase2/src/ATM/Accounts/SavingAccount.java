package ATM.Accounts;

import ATM.InfoHandling.InfoManager;

/**
 * Saving account class
 */
public class SavingAccount extends AssetAccount {
    private ISaverPlan iSaverPlan;
    private final String accountNum;

    /**
     * Constructor of saving account
     * Create a new saving account with ownerID and ISaverPlan
     *
     * @param ownerID the ID of the owner
     * @param the_plan the plan which result in the value of interest
     * @param totalNumAcc the total number of accounts created
     */
    public SavingAccount(String ownerID, ISaverPlan the_plan, int totalNumAcc){
        super(ownerID);
        this.iSaverPlan = the_plan;
        this.accountNum = "004" + getOwnerID() +  String.valueOf(totalNumAcc) + 1;
        setBalance(iSaverPlan.compute(getBalance()));
    }

    /**Getter method for available credit */
    @Override
    public double getAvailableCredit() {
        return getBalance();
    }

    /**Getter method for account number */
    @Override
    public String getAccountNum(){
        return accountNum;
    }

    /**Setter method for saver plan */
    public void setSaverPlan(ISaverPlan s) {
        this.iSaverPlan = s;
    }

    /**@string a string combined with account type, account number */
    @Override
    public String toString() {
        return ("Saving Account " + this.accountNum);
    }

    /** @return a String that includes the summary of saving account: type, number, balance */
    public String getSummary() {
        return (this.toString() + " , Remaining Balance: " + getBalance());
    }
}
