package ATM.Transactions;


import ATM.Accounts.Account;
import ATM.Accounts.TransferTypes.Payable;
import ATM.InfoHandling.BillWriter;
import ATM.org.openexchangerates.oerjava.OpenExchangeRates;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/***
 * PayBill class
 */
public class PayBill extends Transaction{
    private final String to;
    private final Payable fromAcc;
    private final Account toAcc;
    private final LocalDateTime time;
    private final double amount;
    private  BillWriter writer = new BillWriter();
    private String fromCurrency;
    private double exRate = 1;
    private final String baseCurrency = "CAD";


    /***
     * Create a new PayBill.
     *
     * @param fromAccount the account where fund will be extracted from.
     * @param amount the amount of fund will be paid.
     */
    public PayBill(Payable fromAccount, String to, double amount) {
        this.amount = amount;
        this.fromAcc = fromAccount;
        this.toAcc = null;
        this.to = to;
        this.time = LocalDateTime.now();
        this.fromCurrency = ((Account)fromAccount).getBaseCurrency();
        if(this.fromCurrency != baseCurrency){
            this.exRate = exchangeToBaseCurrency(fromCurrency,baseCurrency);
        }
    }

    public double getAmount() {
        return amount;
    }

    /***
     * Get the from Account (which will pay the bill).
     * @return Account if there is a from Account, null if there is not.
     */
    public Account getFromAcc() {
        return (Account)fromAcc;
    }

    /***
     * Get the to Account (Here is null).
     * @return Account if there is a to Account, null if there is not.
     */
    public Account getToAcc() {
        return toAcc;
    }

    /***
     * Get the time when this PayBill happened.
     * @return the time recorded.
     */
    public LocalDateTime getTime() {
        return time;
    }

    /***
     * Execute this PayBill. Set field happened as true if this
     * PayBill is executed.
     * @throws TransactionAmountOverLimitException
     */
    @Override
    void begin() throws TransactionAmountOverLimitException{
        if (amount*exRate > getFromAcc().getAvailableCredit()) {
            throw new TransactionAmountOverLimitException();
        }
        fromAcc.pay(this.getAmount()*exRate);
        setHappened(true);
        writer.write(this.toString());
    }


    /***
     * Not possible to reverse a PayBill
     * @throws ReverseNotPossibleException raised when not enough money in original to account.
     */
    @Override
    public Transaction reverse() throws ReverseNotPossibleException{
        throw new ReverseNotPossibleException();
    }

    /***
     * Get the destination of this bill payment.
     * @return the name whom this payment is made to.
     */
    public String getTo() {
        return to;
    }

    /*public String record(String cont) {
        writer.write();
        String userId = getFromAcc().getOwnerID();
        return (userId + "," + getFromAcc() + "," + getTo() + ","
                + getTime() + "," + getAmount() + "\n")
                ;
    }*/

    /***
     * Return a string representation of PayBill.
     * @return a String.
     */
    @Override
    public String toString() {
        return "PayBill{" +
                "from: " + getFromAcc() +
                ", to: " + getTo()  +
                ", time: " + getTime() +
                ", amount: " + getAmount() +
                "}";
    }
}
