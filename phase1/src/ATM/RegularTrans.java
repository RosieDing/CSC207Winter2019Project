package ATM;

import java.time.LocalDateTime;

public class RegularTrans extends Transaction {
    private final Account fromAcc;
    private final Account toAcc;
    private final LocalDateTime time;

    public RegularTrans(Account fromAcc, Account toAcc, double amount) {
        super(amount);
        this.fromAcc = fromAcc;
        this.toAcc = toAcc;
        time = this.fromAcc.getCurrentTime();
    }
/*
    public int getFromAccNum() {
        return fromAcc;
    }

    public ATM.Account getToAcc() {
        return toAcc;
    }*/

    public Account getFromAcc() {
        return fromAcc;
    }

    public Account getToAcc() {
        return toAcc;
    }

    public LocalDateTime getTime() {
        return time;
    }

    @Override
    void begin(){
        fromAcc.transferOut(this.getAmount());
        toAcc.transferIn(this.getAmount());
    }

    @Override
    public Transaction reverse() {
        Account toAcc = this.fromAcc;
        Account fromAcc = this.toAcc;
        return new RegularTrans(fromAcc, toAcc, this.getAmount());
    }

    @Override
    public String toString() {
        return "ATM.Transaction{" +
                "from: " + getFromAcc() +
                ", to: " + getToAcc() +
                ", time: " + getTime() +
                ", amount: " + getAmount() +
                "}\n";
    }
}
