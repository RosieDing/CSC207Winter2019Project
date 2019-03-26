package ATM.Accounts;

import ATM.Accounts.ISaverPlans.ISaverPlan;

public class GICAccount extends InvestmentAccount implements ISaverPlan {

    String gic_code = "005";
    /**Abstract Method for getting available credit from account */
    public double getAvailableCredit(){}

    /**Abstract Method for getting account number from account */
    public String getAccountNum(){}

    /**Abstract Method for getting balance from account */
    public double getBalance(){}

    /**Abstract Method for setting balance of account */
    public abstract void setBalance(double amount);

    /**Abstract Method for transferring money to account */
    public abstract void transferIn(double amount);

    public abstract String getSummary();

    /** Abstract method for calculating the net balance*/
    public abstract double getNetBalance();
}
