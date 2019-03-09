package ATM;

import java.time.LocalDateTime;

public class Withdrawal extends Transaction {
    private final Withdrawable fromAcc;
    private final Account toAcc;
    private final LocalDateTime time;

    public Withdrawal(Withdrawable fromAcc, double amount) {
        super(amount);
        this.fromAcc = fromAcc;
        this.toAcc = null;
        this.time = LocalDateTime.now();
    }

    public Account getFromAcc() {
        return (Account)fromAcc;
    }

    public Account getToAcc() {
        return toAcc;
    }

    public LocalDateTime getTime() {
        return time;
    }

    /*public int getToAccNum() {
        return toAccNum;
    }

    public int getFromAccNum() {
        return fromAccNum;
    }*/

    @Override
    void begin() throws TransactionAmountOverLimitException{
        Account acc = getFromAcc();
        if (acc instanceof ChequingAccount) {
            if (acc.getBalance() <= 0) {
                throw new TransactionAmountOverLimitException();
            }
        }
        if (getAmount() > acc.getAvailableCredit()) {
            throw new TransactionAmountOverLimitException();
        }
        getFromAcc().withdraw(this.getAmount());
    }

    @Override
    public Deposit reverse() {
        Account toAcc = getFromAcc();
        return new Deposit(toAcc, this.getAmount());
    }

    @Override
    public String toString() {
        return "Withdrawal{" +
                "from: " + getFromAcc() +
                ", time: " + getTime() +
                ", amount: " + getAmount() +
                "}\n";
    }
}
