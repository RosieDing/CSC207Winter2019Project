public class Deposit extends Transaction{

    public Deposit(int toAccount, double amount) {
        super(amount);
        this.setToAccNum(toAccount);
    }

    @Override
    void begin() {
        Loader.getAccount(this.getToAccNum()).transferIn(this.getAmount());
    }

    @Override
    public Withdrawal reverse() {
        int fromAcc = this.getToAccNum();
        return new Withdrawal(fromAcc, this.getAmount());
    }

    @Override
    public String toString() {
        Account acc = Loader.getAccount(this.getToAccNum());
        int userId = acc.getOwnerID();
        return (userId + "," + this.getToAccNum() + "time"
                + "," + this.getAmount() + "\n")
                ;
    }
}
