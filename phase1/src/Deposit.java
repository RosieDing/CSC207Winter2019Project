import java.time.LocalDateTime;
import java.util.Date;

public class Deposit extends Transaction{
    private final Account fromAcc;
    private final Account toAcc;
    private final LocalDateTime time;

    public Deposit(Account toAccount, double amount) {
        super(amount);
        this.toAcc = toAccount;
        this.fromAcc = null;
        this.time = toAcc.getCurrentTime();
    }

    public Deposit(User user, double amount) {
        super(amount);
        this.toAcc = user.getAccManager().getPrimaryChq();
        this.fromAcc = null;
        this.time = toAcc.getCurrentTime();
    }

    /*public int getFromAccNum() {
        return fromAccNum;
    }*/

    /*public int getToAccNum() {
        return toAccNum;
    }*/

    public Account getToAcc() {
        return toAcc;
    }

    public LocalDateTime getTime() {
        return time;
    }

    @Override
    void begin() {
        getToAcc().transferIn(this.getAmount());
    }

    @Override
    public Withdrawal reverse() {
        Account fromAcc = getToAcc();
        return new Withdrawal(fromAcc, this.getAmount());
    }

    public String record() {
        int userId = getToAcc().getOwnerID();
        return (userId + "," + getToAcc() + "," + getTime()
                + "," + this.getAmount() + "\n");
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "to: " + getToAcc() +
                ", time:  " + getTime() +
                ", amount:" + getAmount() +
                '}';
    }
}
