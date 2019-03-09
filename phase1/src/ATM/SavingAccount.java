package ATM;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;

public class SavingAccount extends AssetAccount{
    private int ownerID;
    private double interestRate = 0.001;

    private User owner= Loader.get(ownerID);
    private String id = "004" + ownerID + String.valueOf(owner.getAccountNum + 1);
    private final int accountNum = Integer.valueOf(id);

    public SavingAccount(int ownerID){
        super(ownerID);
        this.ownerID = ownerID;
        double balance = getBalance();
        setBalance(payinterest(balance));
    }

    @Override
    double getAvailableCredit() {
        return getBalance();
    }

    @Override
    public int getAccountNum(){
        return accountNum;
    }


    private double payinterest(double balance){
        if (getCurrentTime().getDayOfMonth() == 1){
            return balance * interestRate;
        }
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
        setChanged();
        notifyObservers();
    }

}
