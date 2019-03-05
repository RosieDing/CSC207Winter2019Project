import javax.print.attribute.standard.DateTimeAtCreation;
import java.io.Serializable;

public abstract class Account implements Withdrawable, TransferInable,Payable, Serializable {
    private int accountNum;
    private int balance;
    private String dateOfCreationg;
    private int ownerID;

    void Account(int id, int ownerID){
        this.ownerID = ownerID;
    }

    abstract int getBalance();

    abstract void setBalance();

    void setDateOfCreation(String date){
        this.dateOfCreationg = date;
    }
    public String getDateOfCreation(){
        return dateOfCreationg;
    }
    public abstract void transferIn(int amount);

    public abstract void pay(int amount);

    public abstract void withdraw(int amount);

    abstract int getAccountNum();

    public int getOwnerID(){return ownerID;}

    public void setOwnerID(int new_owner){ this.ownerID = new_owner;}

}
