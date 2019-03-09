package ATM.Accounts;


import ATM.BankIdentities.User;
import ATM.loading.Loader;
/**
 * Saving account class
 */


public class SavingAccount extends AssetAccount {
    private int ownerID;
    private double availableCredit = getBalance();
//    private double interestRate = 0.001;
    private ISaverPlan iSaverPlan;
    private User owner= Loader.get(ownerID);
    private String id = "004" + ownerID + String.valueOf(owner.getAccountNum() + 1);
    private final int accountNum = Integer.valueOf(id);

    public SavingAccount(int ownerID, ISaverPlan s){
        super(ownerID);
        this.ownerID = ownerID;
        this.iSaverPlan = s;
        setBalance(iSaverPlan.compute(getBalance()));
    }

    private double payinterest(double interestRate, double balance){
        if (getCurrentTime().getDayOfMonth() == 1){
            return balance * interestRate;
        }
        return balance;
    }

    @Override
    double getAvailableCredit() {
        return availableCredit;
    }

    @Override
    public int getAccountNum(){
        return accountNum;
    }

    public void setiSaverPlan(ISaverPlan s) {
        this.iSaverPlan = s;
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return ("SavingAccount" + ", "  + this.accountNum + ", " + getBalance());
    }

}
