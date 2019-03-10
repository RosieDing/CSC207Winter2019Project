package ATM.Accounts;

/**
 * Line of credit account class
 */
import ATM.BankIdentities.User;
import ATM.loading.InfoManager;


public class LineOfCredit extends DebtAccount implements TransferOutable {
    private int ownerID;
    private String id = "002" + ownerID + (InfoManager.getAccountNum() + 1);
    private final int accountNum = Integer.valueOf(id);


    public LineOfCredit(int ownerID, int limit) {
        super(ownerID, limit);
        this.ownerID = ownerID;
    }
    @Override
    public void transferOut(double amount){
        double newbalance = getBalance() + amount;
        setBalance(newbalance);
        setChanged();
        notifyObservers();
    }

    public int getAccountNum(){
        return this.accountNum;
    }


    @Override
    public String toString() {
        return ("LineOfCredit" + ", "  + this.accountNum + ", " + getBalance());
    }

}