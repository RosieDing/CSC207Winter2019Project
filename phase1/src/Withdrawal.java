public class Withdrawal extends Transaction {

    public Withdrawal(int fromAccount, double amount) {
        super(amount);
        this.setFromAccNum(fromAccount);
    }

    @Override
    void begin() {
        Loader.getAccount(this.getFromAccNum()).transferOut(this.getAmount());
    }

    @Override
    public Deposit reverse() {
        int toAcc = this.getFromAccNum();
        return new Deposit(toAcc, this.getAmount());
    }
}
