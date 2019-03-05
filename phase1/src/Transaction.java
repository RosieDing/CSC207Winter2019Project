public abstract class Transaction {
    private final double amount;

    public Transaction(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    abstract void begin();  //make it default

    public abstract Transaction reverse();

}
