package ATM;

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

    public LocalDateTime getTime() {
        return time;
    }

    @Override
    void begin() {
        fromAcc.pay(this.getAmount());
    }

    @Override
    public Transaction reverse(){
        return null;
        //throws exception?
    }

    public String getTo() {
        return to;
    }

    public String record() {
        int userId = getFromAcc().getOwnerID();
        return (userId + "," + getFromAcc() + "," + getTo() + ","
                + getTime() + "," + getAmount() + "\n")
                ;
    }

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
