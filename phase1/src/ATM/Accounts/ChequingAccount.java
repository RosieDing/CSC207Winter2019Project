package ATM.Accounts;


import ATM.BankIdentities.User;
import ATM.InfoHandling.InfoManager;

/**
 * Chequing account class
 */
public class ChequingAccount extends AssetAccount{
    private double overDraftLimit = 100;
    private double availableCredit = overDraftLimit + getBalance();
    private int ownerID;
    private InfoManager l = InfoManager.getInfoManager();
    private User owner= InfoManager.getUser(ownerID);
    private String id = "003" + ownerID + String.valueOf(owner.getAccountNum + 1);
    private final int accountNum = Integer.valueOf(id);

    ChequingAccount(int ownerID){
        super(ownerID);
        this.ownerID = ownerID;
    }


    public void setoverDraftLimit(double limit){
        this.overDraftLimit = limit;
        setChanged();
        notifyObservers();
    }

    public double getOverDraftLimit() {
        return overDraftLimit;
    }

    @Override
    public double getAvailableCredit(){
        return availableCredit;
    }
    @Override
    public int getAccountNum(){
        return accountNum;
    }
    @Override
    public String toString() {
        return ("ATM.Accounts.ChequingAccount" + ", "  + this.accountNum + ", " + getBalance());
    }

}
