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

    public ExchangeCurrency(TransferOutable fromAcc, TransferInable toAcc, Double amount) {
        this.amount = amount;
        this.fromAcc = fromAcc;
        this.toAcc = toAcc;
        this.fromCurrency = ((Account)fromAcc).getBaseCurrency();
        this.toCurrency = ((Account)fromAcc).getBaseCurrency();
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

        OpenExchangeRates oer = new OpenExchangeRates();
        BigDecimal rate = oer.getExchangeRate(getFromCurrency(), getToCurrency());
        if (rate != null){
            double amount = getAmount()*(rate.doubleValue());
            if (amount > acc.getAvailableCredit()) {
                throw new TransactionAmountOverLimitException();
            }
            fromAcc.transferOut(amount);
            toAcc.transferIn(getAmount());
            setHappened(true);
        } else {
            System.out.println("Can not make this conversion.");
        }
    }

    /***
     * Return a new RegularTrans as a reverse of the input transaction (same amount,
     * reversed from and to account).
     * @throws ReverseNotPossibleException raised when not enough money in original to account.
     */
    @Override
    public ExchangeCurrency reverse() throws ReverseNotPossibleException {
        String fromCurrency = this.getToCurrency();
        String toCurrency = this.getFromCurrency();
        TransferOutable fromAcc;
        if (!(getToAcc() instanceof TransferOutable)) {
            throw new ReverseNotPossibleException();
        } else {
            fromAcc = (TransferOutable)this.getToAcc();
        }
        return new ExchangeCurrency(fromAcc, getFromAcc(), fromCurrency, toCurrency, getAmount());
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
