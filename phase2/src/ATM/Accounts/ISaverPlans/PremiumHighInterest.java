package ATM.Accounts.ISaverPlans;

import ATM.BankSystem.Time;

/**High interest plan with monthly interesting rate above 3%*/
public class PremiumHighInterest implements ISaverPlan {

    private double interestRate = 0.005;
    private Time time = Time.getTime();

    /**
     * Constructor for the monthly interest class
     * Creates monthly interest with interest rate
     */
    public PremiumHighInterest(){
    }

    /** Calculate and return the amount of money that is earned from interest */
    @Override
    public double compute(double amount) {
        if (time.getSystemCurrentTime().getDayOfMonth() == 1){
            return amount * interestRate;
        }
        return amount;
    }

    /** Return a String representation of this saving plan
     * @return String information about this saving plan */
    public String toString(){
        return "This is a SavingPlan with monthly interest: " + interestRate;}
}
