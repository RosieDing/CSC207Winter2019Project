package ATM;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;

public class SavingAccount extends AssetAccount{
    private int ownerID;
    private double interestRate = 0.001;
    private double balance;
    private User owner= Loader.get(ownerID);
    private String id = "004" + ownerID + String.valueOf(owner.getAccountNum + 1);
    private final int AccountNum = Integer.valueOf(id);

    public SavingAccount(int ownerID){
        super(ownerID);
        this.ownerID = ownerID;
        payinterest(balance);
    }



    @Override
    public void setBalance(double balance) {
        this.balance = balance;
        setChanged();
        notifyObservers();
    }

    @Override
    public double getAvailableCredit(){
        return balance;
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

    private void payinterest(double balance){
        if (getCurrentTime().getDayOfMonth() == 1){
            this.balance = this.balance * interestRate;
            setChanged();
            notifyObservers();
        }
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

}
