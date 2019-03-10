package ATM.Transactions;

import ATM.Accounts.Account;
import ATM.Accounts.Payable;

import java.time.LocalDateTime;

public class PayBill extends Transaction{
    private final String to;
    private final Payable fromAcc;
    private final Account toAcc;
    private final LocalDateTime time;

    public PayBill(Payable fromAccount, String to, double amount) {
        super(amount);
        this.fromAcc = fromAccount;
        this.toAcc = null;
        this.to = to;
        this.time = LocalDateTime.now();
    }

    /*public int getToAccNum() {
        return toAccNum;
    }

    public int getFromAccNum(){ return fromAccNum; }*/

    public Account getFromAcc() {
        return (Account)fromAcc;
    }

    public Account getToAcc() {
        return toAcc;
    }

    public LocalDateTime getTime() {
        return time;
    }

    @Override
    void begin() throws TransactionAmountOverLimitException{
        if (getAmount() > getFromAcc().getAvailableCredit()) {
            throw new TransactionAmountOverLimitException();
        }
        fromAcc.pay(this.getAmount());
        setHappened(true);
    }

    @Override
    public Transaction reverse() throws ReverseNotPossibleException{
        throw new ReverseNotPossibleException();
    }

    public String getTo() {
        return to;
    }

    /*public String record() {
        String userId = getFromAcc().getOwnerID();
        return (userId + "," + getFromAcc() + "," + getTo() + ","
                + getTime() + "," + getAmount() + "\n")
                ;
    }*/

    @Override
    public String toString() {
        return "PayBill{" +
                "from: " + getFromAcc() +
                ", to: " + getTo()  +
                ", time: " + getTime() +
                ", amount: " + getAmount() +
                "}\n";
    }
}
