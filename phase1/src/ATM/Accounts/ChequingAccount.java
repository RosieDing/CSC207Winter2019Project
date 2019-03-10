package ATM.Accounts;


import ATM.InfoHandling.InfoManager;

/**
 * Chequing account class
 */
public class ChequingAccount extends AssetAccount{
    private double overDraftLimit = 100;
    private double availableCredit = overDraftLimit + getBalance();
    private final String accountNum = "003" + getOwnerID() + (InfoManager.getInfoManager().getAccountNum() + 1);

    /**Constructor for chequing account class*/
    public ChequingAccount(String ownerID){
        super(ownerID);
    }


    public void setOverDraftLimit(double limit){
        this.overDraftLimit = limit;
    }

    public double getOverDraftLimit() {return overDraftLimit;
    }

    @Override
    public double getAvailableCredit(){return availableCredit;}
    @Override
    public String getAccountNum(){
        return accountNum;
    }
    @Override
    public String toString() {
        return ("ChequingAccount" + ", "  + this.accountNum + ", " + getBalance());
    }

}
