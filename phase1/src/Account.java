import java.io.Serializable;
import java.time.LocalDate;


public abstract class Account implements Withdrawable, TransferInable,Payable, Serializable {
    private int accountNum;
    private double balance;
    private LocalDate currentTime = LocalDate.now();
    private final LocalDate dateOfCreation = currentTime;
    private int ownerID;

    void Account(int ownerID){
        this.ownerID = ownerID;
    }
    abstract int getBalance();

    abstract void setBalance();

    private int getDate(LocalDate date){
        String day = String.valueOf(date.getDayOfMonth());
        String month = String.valueOf(date.getMonthValue());
        String year = String.valueOf(date.getYear());
        String stringdate = day+ month+ year;
        return Integer.valueOf(stringdate);

    }

    public int getCurrentDate(){
        return getDate(currentTime);
    }

    public int getDateOfCreation(){
        return getDate(dateOfCreation);
    }

    public abstract void transferIn(int amount);

    public abstract void pay(int amount);

    public abstract void withdraw(int amount);

    abstract int getAccountNum();

    public int getOwnerID(){return ownerID;}
}
