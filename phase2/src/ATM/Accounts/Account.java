package ATM.Accounts;

import ATM.Accounts.TransferTypes.Depositable;
import ATM.Accounts.TransferTypes.Payable;
import ATM.Accounts.TransferTypes.TransferInable;
import ATM.Accounts.TransferTypes.Withdrawable;
import ATM.BankSystem.Time;

import java.io.Serializable;
import java.time.LocalDate;


/**
 * An abstract class that represents a bank account
 */
public abstract class Account implements Withdrawable, TransferInable, Payable, Depositable, Serializable {

    /**The time when an account is created */
    Time time = new Time();
    private final LocalDate dateOfCreation = time.getSystemCurrentTime();

    /**The number of owners*/
    private int numOwner;

    /**The ID of the User whom this account belongs to */
    private String[] ownerID = new String[numOwner];


    /**
     * Constructor of account
     * Create a new account with ownerID
     *
     * @param ownerID the ID of the owner
     */
    public Account(String[] ownerID, int numOwner){
        this.ownerID = ownerID;
    }

    /**Return the User ID */
    public String getOwnerID(){
        String s = new String();
        for(int i = 0; i < ownerID.length; i++){
            String x = ownerID[i];
            s += x;
        }
        return s;
    }

    /**Return the number of users */
    public int getNumOwner(){return numOwner;}

    /**Return the date of Creation */
    public LocalDate getDateOfCreation(){
        return dateOfCreation;
    }

    /**Abstract Method for deposit money to account*/
    public abstract void deposit(int amount);

    /**Abstract Method for getting available credit from account */
    public abstract double getAvailableCredit();

    /**Abstract Method for getting account number from account */
    public abstract String getAccountNum();

    /**Abstract Method for getting balance from account */
    public abstract double getBalance();

    /**Abstract Method for setting balance of account */
    public abstract void setBalance(double amount);

    /**Abstract Method for transferring money to account */
    public abstract void transferIn(double amount);

    /**Abstract Method for paying money from account */
    public abstract void pay(double amount);

    /**Abstract Method for withdraw money from account */
    public abstract void withdraw(int amount);

    public abstract String getSummary();

    /** Abstract method for calculating the net balance*/
    public abstract double getnetbalance();
}
