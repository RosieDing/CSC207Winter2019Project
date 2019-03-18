package ATM.Accounts;

/**
 * Debt account abstract class
 */
public abstract class DebtAccount extends Account {
    private double limit;
    private double balance;

    /**
     * Constructor of debt account
     * Create a new debt account with ownerID and limit
     *
     * @param ownerID the ID of the owner
     * @param  limit the limit of the debt account
     */
    public DebtAccount(String ownerID, double limit){
        super(ownerID);
        this.limit = limit;
    }

    /**Getter method for account balance */
    public double getBalance(){
        return this.balance;
    }

    /**Setter method for account balance */
    public void setBalance(double newBalance){
        this.balance = newBalance;
    }

    /**Getter method for account available credit to spend*/
    @Override
    public double getAvailableCredit() {
        return (limit-getBalance());
    }

    /**Transfer in method for transferring money into debt accounts*/
    @Override
    public void transferIn(double amount) {
        this.balance -= amount;
    }

    @Override
    public void deposit(int amount){
        this.balance -= amount;
    }

    /**Pay method for paying with debt account*/
    @Override
    public void pay(double amount){
        this.balance += amount;
    }

    /**Withdraw method for withdrawing with debt account*/
    @Override
    public void withdraw(int amount){
        this.balance += amount;
    }

    /**Getter method for limit of the debt account */
    public double getLimit(){
        return this.limit;
    }

    /**Setter method for limit of the debt account*/
    public void setLimit(double newLimit){
        this.limit = newLimit;
    }

    /** method for calculating the net balance*/
    @Override
    public double getnetbalance(){
        return -balance;
    }
}