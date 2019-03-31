package ATM.Accounts.Plans.MonthlySavingPlans;

import ATM.Accounts.Plans.Plan;
import ATM.BankSystem.Date;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * The normal saving plans with monthly interest less than 3%
 */

public class MonthlyInterest implements Plan, Serializable {
    private final double interestRate = 0.01;

    /** Calculate and return the amount of money that is earned from interest */
    @Override
    public double compute(double amount) {
        LocalDate current = Date.getDate().getSystemCurrentTime();
        if (current.getDayOfMonth() == 1){
                return amount * (interestRate+1);
            }
            return amount;
    }

    /** Return a String representation of this saving plan
     * @return String information about this saving plan */
    public String toString(){
        return "This is a SavingPlan with monthly interest: "+interestRate;}
    }

