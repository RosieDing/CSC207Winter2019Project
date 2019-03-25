package ATM.Transactions;

import ATM.Accounts.Account;
import ATM.Accounts.ChequingAccount;
import ATM.Accounts.TransferTypes.TransferInable;
import ATM.Accounts.TransferTypes.TransferOutable;
import ATM.BankSystem.Time;
import ATM.org.openexchangerates.oerjava.OpenExchangeRates;
import ATM.org.openexchangerates.oerjava.exceptions.UnavailableExchangeRateException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

public class ExchangeCurrency extends Transaction{
    final private TransferOutable fromAcc;
    final private TransferInable toAcc;
    final private String fromCurrency;
    final private String toCurrency;
    final private double amount;
    final private LocalDate time = Time.getTime().getSystemCurrentTime();

    public ExchangeCurrency(TransferOutable fromAcc, TransferInable toAcc, String fromCurrency,
                            String toCurrency, Double amount) {
        this.amount = amount;
        this.fromAcc = fromAcc;
        this.toAcc = toAcc;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
    }

    public Account getFromAcc() {
        return (Account)fromAcc;
    }

    public Account getToAcc() {
        return (Account)toAcc;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public LocalDate getTime() {
        return time;
    }

    public double getAmount() {
        return amount;
    }

    /***
     * Execute this Withdrawal. Set field happened as true if this
     * Withdrawal is executed.
     * @throws TransactionAmountOverLimitException if the amount is too large.
     */
    @Override
    void begin() throws TransactionAmountOverLimitException {
        Account acc = getFromAcc();
        if (acc instanceof ChequingAccount) {
            if (acc.getBalance() < 0) {
                throw new TransactionAmountOverLimitException();
            }
        }
        double amount = getAmount()/(getRate(getFromCurrency(),getToCurrency()).doubleValue());
        if (amount > acc.getAvailableCredit()) {
            throw new TransactionAmountOverLimitException();
        }
        fromAcc.transferOut(amount);
        toAcc.transferIn(getAmount());
        setHappened(true);
    }

    private BigDecimal getRate(String fromCurrency, String toCurrency){
        OpenExchangeRates oer = new OpenExchangeRates();
        BigDecimal rate = null;
        try {
            if (!fromCurrency.equals(oer.getBase())){
                oer.setBase(fromCurrency);
            }
            rate = oer.currency(toCurrency);
        } catch (UnavailableExchangeRateException e){
            System.out.println(e.getMessage());
        }
        return rate;
    }

    /***
     * Return a new RegularTrans as a reverse of the input transaction (same amount,
     * reversed from and to account).
     * @throws ReverseNotPossibleException raised when not enough money in original to account.
     */
    @Override
    public RegularTrans reverse() throws ReverseNotPossibleException {
        throw new ReverseNotPossibleException();
    }

    @Override
    public String toString() {
        return "ExchangeCurrency{" +
                "fromAcc=" + fromAcc +
                ", toAcc=" + toAcc +
                ", fromCurrency='" + fromCurrency + '\'' +
                ", toCurrency='" + toCurrency + '\'' +
                ", amount=" + amount +
                ", time=" + time +
                '}';
    }
}
