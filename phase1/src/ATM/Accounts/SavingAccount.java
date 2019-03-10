package ATM.Accounts;

import ATM.InfoHandling.InfoManager;



public class SavingAccount extends AssetAccount {
    private int ownerID;
    private double availableCredit = getBalance();
    private ISaverPlan iSaverPlan;
    private String id = "004" + ownerID + (InfoManager.getAccountNum() + 1);
    private final String accountNum = id;

    public SavingAccount(int ownerID, ISaverPlan s){
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
