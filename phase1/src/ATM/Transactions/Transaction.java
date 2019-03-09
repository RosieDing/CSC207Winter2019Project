package ATM.Transactions;

import ATM.Accounts.Account;

public abstract class Transaction {
    private final double amount;

    public Transaction(double amount) {
        this.amount = amount;
    }

    //public abstract int getFromAccNum();

    //public abstract int getToAccNum();

    public abstract Account getFromAcc();

    public abstract Account getToAcc();

    public double getAmount() {
        return amount;
    }

    abstract void begin() throws TransactionAmountOverLimitException;  //make it default

    public abstract Transaction reverse() throws ReverseNotPossibleException;

}
