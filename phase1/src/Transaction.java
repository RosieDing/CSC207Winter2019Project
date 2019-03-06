public abstract class Transaction {
    private final double amount;

    public Transaction(double amount) {
        this.amount = amount;
    }

    public abstract int getFromAccNum();

    public abstract int getToAccNum();

    public double getAmount() {
        return amount;
    }

    abstract void begin();  //make it default

    public abstract Transaction reverse();

    public abstract

}
