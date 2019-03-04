import java.io.Serializable;

public abstract class Account implements Withdrawable, TransferInable,Payable, Serializable {
    private int accountNum;
    private int balance;
    private String dateOfCreationg;
    private int ownerID;

    abstract void Account(int id, int ownerID);
    abstract int getBalance();
    abstract void setBalance();
    abstract String getDateOfCreationg();
    public abstract void transferIn(int amount);
    public abstract void pay(int amount);
    public abstract void withdraw(int amount);
    abstract int getAccountNum();
}
