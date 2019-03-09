package ATM.Accounts;

/**
 * Debt account abstract class
 */
public abstract class DebtAccount extends Account {
    private double limit;
    private int ownerID;
    private double balance;
    private double availableCredit = limit - balance;

    /**Constructor for debt account class */
    public DebtAccount(int ownerID, double limit){
        super(ownerID);
        this.limit = limit;
        this.availableCredit = limit - balance;
        this.balance = 0;
    }

    /**Getter for account balance */
    public double getBalance(){
        return this.balance;
    }

    /**Setter for account balance */
    public void setBalance(double newBalance){
        this.balance = newBalance;
        setChanged();
        notifyObservers();
    }

    /**Getter for account available credit to spend*/
    @Override
    public double getAvailableCredit() {
        return availableCredit;
    }

    /**Transfer in method for transfering money into debt accounts*/
    @Override
    public void transferIn(double amount){
        this.balance -= amount;
        setChanged();
        notifyObservers();
    }

    @Override
    public void pay(double amount){
        this.balance += amount;
        setChanged();
        notifyObservers();
    }

    @Override
    public void withdraw(double amount){
        this.balance += amount;
        setChanged();
        notifyObservers();
    }
    public double getLimit(){
        return this.limit;
    }

    public void setLimit(double newLimit){
        this.limit = newLimit;
        setChanged();
        notifyObservers();
    }

}