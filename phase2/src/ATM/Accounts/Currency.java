package ATM.Accounts;

import ATM.org.openexchangerates.oerjava.OpenExchangeRates;

import java.math.BigDecimal;

public class Currency {

    private String type = "CAD";
    private double amount;

    public Currency(double amount){
        this.amount = amount;
    }

    public Currency(String type, double amount){
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void addAmount(double amount){
        this.amount += amount;
    }

    public void add(Currency other){
        double rate = exchangeToBaseCurrency(other.getType(), this.getType());
        this.amount += other.getAmount() * rate;
    }

    public void subtract(Currency other){
        double rate = exchangeToBaseCurrency(other.getType(), this.getType());
        this.amount -= other.getAmount() * rate;
    }

    protected double exchangeToBaseCurrency(String billsCurrency,String baseCurrency){
        OpenExchangeRates oer = new OpenExchangeRates();
        BigDecimal rateDecial = oer.getExchangeRate(billsCurrency, baseCurrency);
        return rateDecial.doubleValue();
    }
    public String toString(){
        return this.type + ": " + this.amount;
    }
}
