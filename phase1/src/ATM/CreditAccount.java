package ATM;

public class CreditAccount extends DebtAccount {
    private int accountNum;
    private double balance;
    private String dateOfCreation;
    private int ownerID;
    private double limit;


    public CreditAccount(int ownerID, int limit){
        super(ownerID, limit);
    }
    public int getBalance(){
        return this.balance;
    }
    public void setBalance(double newBalance){
        this.balance = newBalance;
    }
    public String getDateOfCreation() {
        return dateOfCreation;
    }

    @Override
    public void transferIn(double amount){
        this.balance -= amount;
    }

    @Override
    public void pay(double amount){
        double overornot;
        overornot = this.balance + amount;
        if (overornot <= this.limit){
            this.balance += amount;
        }
    }

    @Override
    public void withdraw(double amount){
        double overornot;
        overornot = this.balance + amount;
        if (overornot <= this.limit){
            this.balance += amount;
        }
    }
    public int getAccountNum(){
        return this.accountNum;
    }
    public int getOwnerID(){
        return this.ownerID;
    }

    public int getLimit(){
        return this.limit;
    }
    public void setLimit(double newLimit){
        this.limit = newLimit;
    }

    @Override
    public String toString() {
        return ("ATM.CreditAccount" + ", "  + this.accountNum + ", " + this.balance;
    }

}