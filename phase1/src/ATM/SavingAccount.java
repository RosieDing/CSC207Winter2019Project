package ATM;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;

public class SavingAccount extends AssetAccount{
    private int ownerID;
    private double availableCredit = getBalance();
    private double interestRate = 0.001;
    private User owner= Loader.get(ownerID);
    private String id = "004" + ownerID + String.valueOf(owner.getAccountNum() + 1);
    private final int accountNum = Integer.valueOf(id);

    private SavingAccount(int ownerID){
        super(ownerID);
        this.ownerID = ownerID;
        double balance = getBalance();
        setBalance(payinterest(interestRate,balance));
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

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return ("ATM.SavingAccount" + ", "  + this.accountNum + ", " + getBalance());
    }

}
