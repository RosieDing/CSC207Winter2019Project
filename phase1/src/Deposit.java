public class Deposit extends Transaction{
    private final int fromAccNum;
    private final int toAccNum;

    public Deposit(int toAccount, double amount) {
        super(amount);
        this.toAccNum = toAccount;
        this.fromAccNum = 0;
    }

    public int getFromAccNum() {
        return fromAccNum;
    }

    public int getToAccNum() {
        return toAccNum;
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
                + "," + this.getAmount() + "\n");
    }

    @Override
    public boolean selfCheck() {
        boolean possible = false;
        if (Loader.getAccount(this.toAccNum) instanceof TransferInable) {
            possible = true;
        }
        return possible;
    }
}
