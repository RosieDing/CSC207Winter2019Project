package ATM;

public abstract class DebtAccount extends Account {
    private double limit;
    private int ownerID;
    private double balance;
    private double availableCredit = limit - balance;



    public DebtAccount(int ownerID, double limit){
        super(ownerID);
        this.limit = limit;
    }

    public double getBalance(){
        return this.balance;
    }
    public void setBalance(double newBalance){
        this.balance = newBalance;
        setChanged();
        notifyObservers();
    }

    @Override
    public double getAvailableCredit() {
        return availableCredit;
    }

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