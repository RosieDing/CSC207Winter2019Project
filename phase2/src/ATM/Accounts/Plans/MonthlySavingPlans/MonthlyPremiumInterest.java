package ATM.Accounts.Plans.MonthlySavingPlans;

import ATM.Accounts.Plans.Plan;
import ATM.BankSystem.Date;

import java.io.Serializable;

/**High interest plan with monthly interesting rate above 3%*/
public class MonthlyPremiumInterest implements Plan, Serializable {
    private final double interestRate = 0.03;

    /** Calculate and return the amount of money that is earned from interest */
    @Override
    public double compute(double amount) {
        Date date = Date.getDate();
        if (date.getSystemCurrentTime().getDayOfMonth() == 1){
            return amount * (interestRate+1);
        }
        return amount;
    }

    /** Return a String representation of this saving plan
     * @return String information about this saving plan */
    public String toString(){
        return "This is a SavingPlan with monthly interest: " + interestRate;
    }
}



