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
        return balance
    }
    public void setBalance(double newBalance){
        balance = newBalance
    }
    public String getDateOfCreation() {
        return dateOfCreation;
    }
    public void transferIn(double amount){
        balance -= amount;
    }
    public void pay(double amount){
        balance += amount;
    }
    public void withdraw(double amount){
        balance += amount;
    }
    public int getAccountNum(){
        return accountNum;
    }
    public int getOwnerID(){
        return ownerID;
    }

    public int getLimit(){
        return limit;
    }
    public void setLimit(double newLimit){
        limit = newLimit;
    }

    @Override
    public String toString() {
        return ("CreditAccount" + ", "  + this.accountNum + ", " + this.balance;
    }

}