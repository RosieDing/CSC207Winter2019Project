package ATM.Accounts;

/**
 * Asset account abstract class
 */
public abstract class AssetAccount extends Account implements TransferOutable {
    private double balance;

    AssetAccount(int ownerID){
           super(ownerID);
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void transferIn(double amount) {
        balance += amount;
    }

    @Override
    public void deposit(double amount){
        balance += amount;
    }

    @Override
    public void transferOut(double amount) {
        balance -= amount;
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public void pay(double amount) {
        balance -= amount;
    }

    @Override
    public void withdraw(double amount) {
        balance -= amount;
    }
}
