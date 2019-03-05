public class Deposit extends Transaction{
    public Deposit(int toAccount, double amount) {
        super(amount);
        this.setToAccNum(toAccount);
    }

    @Override
    public void begin() {
        Loader.getAccount(this.getToAccNum()).transferIn(this.getAmount());
    }

    @Override
    public Transaction reverse() {
        int fromAcc = this.getToAccNum();
        Withdrawal e = new Withdrawal(fromAcc, this.getAmount());
        e.begin();
        return e;
    }
}
