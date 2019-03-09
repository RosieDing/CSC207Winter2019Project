package ATM.Accounts;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Observable;

/**
 * Account abstract class
 */
public abstract class Account extends Observable implements Withdrawable, TransferInable,Payable, Serializable {
    private LocalDateTime currentTime = LocalDateTime.now();
    private final LocalDateTime dateOfCreation = currentTime;
    private int ownerID;

    Account(int ownerID){
        this.ownerID = ownerID;
    }
    public int getOwnerID(){ return ownerID;}

    public LocalDateTime getCurrentTime(){
        return currentTime;
    }

    public LocalDateTime getDateOfCreation(){
        return dateOfCreation;}

    public abstract double getAvailableCredit();

    public abstract int getAccountNum();

    public abstract double getBalance();

    public abstract void transferIn(double amount);

    abstract void setBalance(double amount);

    public abstract void pay(double amount);

    public abstract void withdraw(double amount);
}
