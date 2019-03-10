package ATM.Accounts;

/**
 * Asset account abstract class
 */
public abstract class AssetAccount extends Account implements TransferOutable {
    private double balance;

    /**Constructor for asset account class */
    AssetAccount(int ownerID){
           super(ownerID);
    }

    /**Getter for asset accounts*/
    @Override
    public double getBalance() {
        return balance;
    }

    /**Method for Transfer in money to the asset account*/
    @Override
    public void transferIn(double amount) {
        balance += amount;
        setChanged();
        notifyObservers();
    }

    /**Method for Transfer out money from the asset account*/
    @Override
    public void transferOut(double amount) {
        balance -= amount;
        setChanged();
        notifyObservers();
    }

    /**Setter method for the balance of the asset account*/
    @Override
    public void setBalance(double balance) {
        this.balance = balance;
        setChanged();
        notifyObservers();
    }

    /**Method for pay money from the asset account*/
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
