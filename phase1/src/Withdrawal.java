public class Withdrawal extends Transaction {
    private final int fromAccNum;
    private final int toAccNum;

    public Withdrawal(int fromAccount, double amount) {
        super(amount);
        this.fromAccNum = fromAccount;
        this.toAccNum = 0;
    }

    public int getToAccNum() {
        return toAccNum;
    }

    public int getFromAccNum() {
        return fromAccNum;
    }

    @Override
    void begin() {
        Loader.getAccount(getFromAccNum()).transferOut(this.getAmount());
    }

    @Override
    public Deposit reverse() {
        int toAcc = getFromAccNum();
        return new Deposit(toAcc, this.getAmount());
    }
}
