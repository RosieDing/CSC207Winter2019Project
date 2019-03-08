import java.time.LocalDateTime;
import java.util.Date;

public abstract class Transaction {
    private final double amount;

    public Transaction(double amount) {
        this.amount = amount;
    }

    //public abstract int getFromAccNum();

    //public abstract int getToAccNum();

    public Date getDate() { return date; }

    public double getAmount() {
        return amount;
    }

    abstract void begin();  //make it default

    public abstract Transaction reverse();

}
