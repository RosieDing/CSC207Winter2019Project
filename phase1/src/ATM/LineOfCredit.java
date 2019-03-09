package ATM;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;

public class LineOfCredit extends DebtAccount implements TransferOutable{
    private int ownerID;
    private User owner= Loader.get(ownerID);
    private String id = "002" + ownerID + String.valueOf(owner.getAccountNum() + 1);
    private final int accountNum = Integer.valueOf(id);


    public LineOfCredit(int ownerID, int limit){
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
        return ("ATM.LineOfCredit" + ", "  + this.accountNum + ", " + getBalance());
    }

}