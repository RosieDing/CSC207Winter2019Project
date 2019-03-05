public abstract class Transaction {
    private int fromAccNum;
    private int toAccNum;
    private double amount;

    public Transaction(double amount) {
        this.amount = amount;
    }

    public void setFromAccNum(int fromAccNum) {
        this.fromAccNum = fromAccNum;
    }

    public void setToAccNum(int toAccNum) {
        this.toAccNum = toAccNum;
    }

    public int getFromAccNum() {
        return fromAccNum;
    }

    public int getToAccNum() {
        return toAccNum;
    }

    public double getAmount() {
        return amount;
    }

    public abstract void begin();

    public abstract Transaction reverse() throws PayBill.PayBillReverseException;

}
