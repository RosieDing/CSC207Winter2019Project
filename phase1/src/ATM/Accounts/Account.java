package ATM.Accounts;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * An abstract class that represents a bank account
 */
public abstract class Account implements Withdrawable, TransferInable, Payable, Depositable, Serializable {

    /**The time when an account is created */
    private final LocalDateTime dateOfCreation = LocalDateTime.now();

    /**The ID of the User whom this account belongs to */
    private String ownerID;

    /**Constructor of account */
    public Account(String ownerID){
        this.ownerID = ownerID;
    }

    /**Return the User ID */
    public String getOwnerID(){ return ownerID;}


    /**Return the date of Creation */
    public LocalDateTime getDateOfCreation(){
        return dateOfCreation;
    }

    public abstract void deposit(double amount);

    public abstract double getAvailableCredit();

    public abstract String getAccountNum();

    public abstract double getBalance();

    public abstract void setBalance(double amount);

    public abstract void transferIn(double amount);

    public abstract void pay(double amount);

    public abstract void withdraw(double amount);
}
