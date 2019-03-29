package ATM.Transactions;

import ATM.Accounts.Account;
import ATM.Accounts.TransferTypes.TransferInable;
import ATM.Accounts.TransferTypes.TransferOutable;

import java.time.LocalDateTime;

/***
 * RegularTrans class.
 */
public class RegularTrans extends Transaction{
    private final TransferOutable fromAcc;
    private final TransferInable toAcc;
    private final LocalDateTime time;
    private final double amount;
    private String fromCurrency;
    private String toBaseCurrency;
    private double exRate;

    /***
     * Create a new RegularTrans.
     * @param fromAcc transfer from this TransferOutable account.
     * @param toAcc transfer to this TransferInable account.
     * @param amount transaction amount.
     */
    public RegularTrans(TransferOutable fromAcc, TransferInable toAcc, double amount) {
        this.amount = amount;
        this.fromAcc = fromAcc;
        this.toAcc = toAcc;
        time = LocalDateTime.now();
        this.fromCurrency = ((Account)fromAcc).getBaseCurrency();
        this.toBaseCurrency = ((Account)toAcc).getBaseCurrency();
        if(this.fromCurrency != toBaseCurrency){
            this.exRate = exchangeToBaseCurrency(fromCurrency,toBaseCurrency);
        }
    }

    public double getAmount() {
        return amount;
    }

    /***
     * Get the from Account (where money will be transferred out).
     * @return Account if there is a from Account, null if there is not.
     */
    public Account getFromAcc() {
        return ((Account)fromAcc);
    }

    /***
     * Get the to Account (where money will be transferred in).
     * @return Account if there is a to Account, null if there is not.
     */
    public Account getToAcc() {
        return ((Account)toAcc);
    }

    /***
     * Get the time when this RegularTrans happened.
     * @return the time recorded.
     */
    public LocalDateTime getTime() {
        return time;
    }

    /***
     * Execute this RegularTrans. Set field happened as true if this
     * RegularTrans is executed.
     * @throws TransactionAmountOverLimitException
     */
    @Override
    void begin() throws TransactionAmountOverLimitException{
        if (getAmount()*exRate > getFromAcc().getAvailableCredit()) {
            throw new TransactionAmountOverLimitException();
        }
        fromAcc.transferOut(getAmount()*exRate);
        toAcc.transferIn(this.getAmount());
        setHappened(true);
    }

    /***
     * Return a new RegularTrans as a reverse of the input transaction (same amount,
     * reversed from and to account).
     * @return RegularTrans the reversed transaction.
     * @throws ReverseNotPossibleException raised when not enough money in original to account.
     */
    @Override
    public RegularTrans reverse() throws ReverseNotPossibleException{
        TransferInable toAcc = getFromAcc();
        TransferOutable from;
        if (getToAcc() instanceof TransferOutable){
            from = (TransferOutable)getToAcc();
        } else {
            throw new ReverseNotPossibleException();
        }
        return new RegularTrans(from, toAcc, this.getAmount());
    }


    /***
     * Return a String representation of RegularTrans
     * @return string
     */
    @Override
    public String toString() {
        return "Transaction{" +
                "from: " + getFromAcc() +
                ", to: " + getToAcc() +
                ", time: " + getTime() +
                ", amount: " + getAmount() +
                "}";
    }
}
