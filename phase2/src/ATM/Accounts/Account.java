package ATM.Accounts;

import ATM.Accounts.TransferTypes.Depositable;
import ATM.Accounts.TransferTypes.Payable;
import ATM.Accounts.TransferTypes.TransferInable;
import ATM.Accounts.TransferTypes.Withdrawable;
import ATM.BankSystem.Time;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 * An abstract class that represents a bank account
 */
public abstract class Account implements Withdrawable, TransferInable, Payable, Depositable, Serializable {

    /**The time when an account is created */
    private final LocalDate dateOfCreation = Time.getTime().getSystemCurrentTime();

    /**The ID of the User whom this account belongs to */
    private ArrayList<String> ownerID;

    /**The number of owners*/
    private int numOwner;


    /**
     * Constructor of account
     * Create a new account with ownerID
     *
     * @param ownerID the ID of the owner
     */
    public Account(ArrayList<String> ownerID){
        this.ownerID = ownerID;
        this.numOwner = ownerID.size();
    }

    /**Return the User ID */
    public ArrayList<String> getOwnerID(){
        return ownerID;
    }


    public void AddOwnerID(String newOwner){
        ownerID.add(newOwner);
    }

    public boolean containsOwner(String owner){
        return ownerID.contains(owner);
    }

    public void removeOwner(String owner){
        this.ownerID.remove(owner);
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
    public abstract double getNetBalance();
}
