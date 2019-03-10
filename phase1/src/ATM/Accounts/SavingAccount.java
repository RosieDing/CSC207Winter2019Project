package ATM.Accounts;

import ATM.InfoHandling.InfoManager;



public class SavingAccount extends AssetAccount {
    private String ownerID;
    private double availableCredit = getBalance();
    private ISaverPlan iSaverPlan;
    private final String accountNum = "004" + ownerID + (InfoManager.getInfoManager().getAccountNum() + 1);

    public SavingAccount(String ownerID, ISaverPlan s){
        super(ownerID);
        this.ownerID = ownerID;
        this.iSaverPlan = s;
        setBalance(iSaverPlan.compute(getBalance()));
    }


    @Override
    public double getAvailableCredit() {
        return availableCredit;
    }

    @Override
    public String getAccountNum(){
        return accountNum;
    }

    public void setiSaverPlan(ISaverPlan s) {
        this.iSaverPlan = s;
    }

    @Override
    public String toString() {
        return ("SavingAccount" + ", "  + this.accountNum + ", " + getBalance());
    }

}
