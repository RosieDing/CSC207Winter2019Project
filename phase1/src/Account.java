import java.io.Serializable;
import java.time.LocalDateTime;


public abstract class Account implements Withdrawable, TransferInable,Payable, Serializable {
    private int accountNum;
    private double balance;
    private double availableCredit;
    private LocalDateTime currentTime = LocalDateTime.now();
    private final LocalDateTime dateOfCreation = currentTime;
    private int ownerID;

    void Account(int ownerID){
        this.ownerID = ownerID;
    }

    abstract void setBalance();

    abstract double getAvailableCredit();

    abstract double getBalance();

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
        return dateOfCreation;
    }

    public abstract void transferIn(int amount);

    public abstract void pay(int amount);

    public abstract void withdraw(int amount);

    abstract int getAccountNum();

    public int getOwnerID(){return ownerID;}
}
