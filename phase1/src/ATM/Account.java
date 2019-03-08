package ATM;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Observable;


public abstract class Account extends Observable implements Withdrawable, TransferInable,Payable, Serializable {
    private int accountNum;
    private double balance;
    private double availableCredit;
    private LocalDateTime currentTime = LocalDateTime.now();
    private final LocalDateTime dateOfCreation = currentTime;
    private int ownerID;

    Account(int ownerID){
        this.ownerID = ownerID;
    }
    public int getOwnerID(){ return ownerID;}

//    private int getDate(LocalDateTime date){
//        String day = String.valueOf(date.getDayOfMonth());
//        String month = String.valueOf(date.getMonthValue());
//        String year = String.valueOf(date.getYear());
//        String stringdate = day+ month+ year;
//        return Integer.valueOf(stringdate);
//    }

    public LocalDateTime getCurrentTime(){
        return currentTime;
    }

    public LocalDateTime getDateOfCreation(){
        return dateOfCreation;}

    abstract double getAvailableCredit();

    public abstract int getAccountNum();

    abstract double getBalance();

    public abstract void transferIn(double amount);

    abstract void setBalance(double amount);

    public abstract void pay(double amount);

    public abstract void withdraw(double amount);
}
