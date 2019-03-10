package ATM.Accounts;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Observable;

/**
 * An abstract class that represents a bank account
 */
public abstract class Account extends Observable implements Withdrawable, TransferInable,Payable, Serializable {

    /**Current system time */
    private LocalDateTime currentTime = LocalDateTime.now();

    /**The time when an account is created */
    private final LocalDateTime dateOfCreation = currentTime;

    /**The ID of the User whom this account belongs to */
    private int ownerID;

    /**Constructor of account */
    public Account(int ownerID){
        this.ownerID = ownerID;
    }

    /**Return the User ID */
    public int getOwnerID(){ return ownerID;}

    /**Return the current time */
    public LocalDateTime getCurrentTime(){
        return currentTime;
    }

    /**Return the date of Creation */
    protected LocalDateTime getDateOfCreation(){
        return dateOfCreation;}

    public abstract double getAvailableCredit();

    public abstract int getAccountNum();

    public abstract double getBalance();

    public abstract void setBalance(double amount);

    public abstract void transferIn(double amount);

    public abstract void pay(double amount);

    public abstract void withdraw(double amount);
}
