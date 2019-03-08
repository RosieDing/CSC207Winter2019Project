package ATM;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;

public class ChequingAccount extends AssetAccount{
    private double balance;
    private double overDraftLimit = 100;
    private double availableCredit = overDraftLimit + balance;
    private int ownerID;
    private User owner= Loader.get(ownerID);
    private String id = "003" + ownerID + String.valueOf(owner.getAccountNum + 1);
    private final int AccountNum = Integer.valueOf(id);

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
    public void setBalance(double balance) {
        this.balance = balance;
        setChanged();
        notifyObservers();
    }

    @Override
    public double getAvailableCredit(){
        return availableCredit;
    }
    @Override
    public int getAccountNum(){
        return AccountNum;
    }

    @Override
    public void transferOut(double amount){
        balance += amount;
        setChanged();
        notifyObservers();
    }

    @Override
    public double getBalance(){
        return balance;
    }

    @Override
    public void transferIn(double amount){
        balance -= amount;
        setChanged();
        notifyObservers();
    }

    @Override
    public void pay(double amount){
        balance -= amount;
        setChanged();
        notifyObservers();
    }

    @Override
    public void withdraw(double amount){
        balance -= amount;
        setChanged();
        notifyObservers();
    }


}
