public class Deposit extends Transaction{
    private final int toAccNum;

    public Deposit(int toAccount, double amount) {
        super(amount);
        this.toAccNum = toAccount;
    }

    @Override
    void begin() {
        Loader.getAccount(this.toAccNum).transferIn(this.getAmount());
    }

    @Override
    public Withdrawal reverse() {
        int fromAcc = this.toAccNum;
        return new Withdrawal(fromAcc, this.getAmount());
    }

    @Override
    public String toString() {
        Account acc = Loader.getAccount(this.toAccNum);
        int userId = acc.getOwnerID();
        return (userId + "," + this.toAccNum + "time"
                + "," + this.getAmount() + "\n")
                ;
    }
}
