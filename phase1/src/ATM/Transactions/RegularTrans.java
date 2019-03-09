package ATM.Transactions;

import ATM.Accounts.Account;
import ATM.Accounts.TransferInable;
import ATM.Accounts.TransferOutable;

import java.time.LocalDateTime;

public class RegularTrans extends Transaction{
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

    public ATM.Accounts.Account getToAcc() {
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
    void begin() throws TransactionAmountOverLimitException{
        if (getAmount() > getFromAcc().getAvailableCredit()) {
            throw new TransactionAmountOverLimitException();
        }
        fromAcc.transferOut(this.getAmount());
        toAcc.transferIn(this.getAmount());
    }

    @Override
    public Transaction reverse() throws ReverseNotPossibleException{
        TransferInable toAcc = getFromAcc();
        if (getToAcc() instanceof TransferOutable){
            TransferOutable fromAcc = (TransferOutable)getToAcc();
        } else {
            throw new ReverseNotPossibleException();
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
