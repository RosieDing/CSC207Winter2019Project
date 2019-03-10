package ATM.Accounts;

import ATM.InfoHandling.InfoManager;

/**
 * Saving account class
 */
public class SavingAccount extends AssetAccount {
    private double availableCredit = getBalance();
    private ISaverPlan iSaverPlan;
    private final String accountNum = "004" + getOwnerID() + (InfoManager.getInfoManager().getAccountNum() + 1);

    /**Constructor for saving account */
    public SavingAccount(String ownerID, ISaverPlan s){
        super(ownerID);
        this.iSaverPlan = s;
        setBalance(iSaverPlan.compute(getBalance()));
    }

    /**Getter method for available credit */
    @Override
    public double getAvailableCredit() {
        return availableCredit;
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

    @Override
    public String toString() {
        return ("SavingAccount" + ", "  + this.accountNum + ", " + getBalance());
    }

}
