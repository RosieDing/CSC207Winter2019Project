package ATM.Transactions;

import ATM.Accounts.Account;
import ATM.Accounts.ChequingAccount;
import ATM.Accounts.Depositable;

import java.time.LocalDateTime;

public class Deposit extends Transaction{
    private final Account fromAcc;
    private final Depositable toAcc;
    private final LocalDateTime time;

    public Deposit(Depositable toAccount, double amount) {
        super(amount);
        this.toAcc = toAccount;
        this.fromAcc = null;
        this.time = LocalDateTime.now();
    }

    /*public Deposit(User user, double amount) {
        super(amount);
        this.toAcc = user.getAccManager().getPrimaryChq();
        this.fromAcc = null;
        this.time = toAcc.getCurrentTime();
    }*/

    /*public int getFromAccNum() {
        return fromAccNum;
    }*/

    /*public int getToAccNum() {
        return toAccNum;
    }*/

    public Account getFromAcc() { return fromAcc; }

    public Account getToAcc() {
        return (Account)toAcc;
    }

    public LocalDateTime getTime() {
        return time;
    }

    /***
     * Execute this transaction. Set field happened as true if
     * this transaction is made.
     */
    @Override
    void begin() {
        getToAcc().deposit(this.getAmount());
        setHappened(true);
    }

    /***
     * Return a new transaction as a reverse of the input transaction (same amount,
     * reversed from and to account). If the original "to account" has not enough
     * money for undoing input transaction, throws ReverseNotPossibleException.
     * @return Withdrawal
     * @throws ReverseNotPossibleException
     */
    @Override
    public Withdrawal reverse() throws ReverseNotPossibleException{
        Account fromAcc = getToAcc();
        if (fromAcc instanceof ChequingAccount) {
            if (fromAcc.getBalance() <=0) {
                throw new ReverseNotPossibleException();
            }
        }
        if (getAmount() > fromAcc.getAvailableCredit()) {
            throw new ReverseNotPossibleException();
        }
        return new Withdrawal(fromAcc, this.getAmount());
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
