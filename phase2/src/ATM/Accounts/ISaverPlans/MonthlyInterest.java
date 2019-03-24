package ATM.Accounts.ISaverPlans;

import ATM.Accounts.ISaverPlan;
import ATM.BankSystem.Time;

import java.io.Serializable;
import java.time.LocalDate;
/**
 * The normal saving plans with monthly interest 3%
 */

public class MonthlyInterest implements ISaverPlan, Serializable {
    private double interestRate;
    private Time time = Time.getTime();

    /**
     * Constructor for the monthly interest class
     * Creates monthly interest with interest rate
     *
     * @param interestRate the interest rate of the monthly interest
     */
    public MonthlyInterest(double interestRate){
        this.interestRate = interestRate;
    }

    /** Calculate and return the amount of money that is earned from interest */
    @Override
    public double compute(double amount) {
        if (time.getSystemCurrentTime().getDayOfMonth() == 1){
                return amount * interestRate;
            }
            return amount;
    }

    public String toString(){
        return "This is a SavingPlan with monthly interest; "+interestRate;}

    }
