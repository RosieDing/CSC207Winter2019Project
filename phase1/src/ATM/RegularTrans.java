package ATM;

import javax.security.auth.login.AccountException;
import java.time.LocalDateTime;

public class RegularTrans extends Transaction {
    private final TransferOutable fromAcc;
    private final TransferInable toAcc;
    private final LocalDateTime time;

    public RegularTrans(TransferOutable fromAcc, TransferInable toAcc, double amount) {
        super(amount);
        this.fromAcc = fromAcc;
        this.toAcc = toAcc;
        time = LocalDateTime.now();
    }
/*
    public int getFromAccNum() {
        return fromAcc;
    }

    public ATM.Account getToAcc() {
        return toAcc;
    }*/

    public Account getFromAcc() {
        return ((Account)fromAcc);
    }

    public Account getToAcc() {
        return ((Account)toAcc);
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
        TransferInable toAcc = getFromAcc();
        if (getToAcc() instanceof TransferOutable){
            TransferOutable fromAcc = (TransferOutable)getToAcc();
        }
        return new RegularTrans(fromAcc, toAcc, this.getAmount());
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "from: " + getFromAcc() +
                ", to: " + getToAcc() +
                ", time: " + getTime() +
                ", amount: " + getAmount() +
                "}\n";
    }
}
