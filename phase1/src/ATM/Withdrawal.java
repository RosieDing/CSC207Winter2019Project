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
    void begin() {
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
