package ATM.Accounts;

/**
 * Asset account class
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
        setChanged();
        notifyObservers();
    }

    @Override
    public void transferOut(double amount) {
        balance -= amount;
        setChanged();
        notifyObservers();
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
        setChanged();
        notifyObservers();
    }

    @Override
    public void pay(double amount) {
        balance -= amount;
        setChanged();
        notifyObservers();
    }

    @Override
    public void withdraw(double amount) {
        balance -= amount;
        setChanged();
        notifyObservers();
    }
}
