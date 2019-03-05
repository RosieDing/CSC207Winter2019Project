public class Withdrawal extends Transaction {
    private final int fromAccNum;

    public Withdrawal(int fromAccount, double amount) {
        super(amount);
        this.fromAccNum = fromAccount;
    }

    @Override
    void begin() {
        Loader.getAccount(this.fromAccNum).transferOut(this.getAmount());
    }

    @Override
    public Deposit reverse() {
        int toAcc = this.fromAccNum;
        return new Deposit(toAcc, this.getAmount());
    }
}
