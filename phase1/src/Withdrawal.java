public class Withdrawal extends Transaction {
    public Withdrawal(int fromAccount, double amount) {
        super(amount);
        this.setFromAccNum(fromAccount);
    }

    @Override
    public void begin() {
        Loader.getAccount(this.getFromAccNum()).transferOut(this.getAmount());
    }

    @Override
    public Deposit reverse() {
        int toAcc = this.getFromAccNum();
        Deposit e = new Deposit(toAcc, this.getAmount());
        e.begin();
        return e;
    }
}
