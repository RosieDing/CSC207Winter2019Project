package ATM.Accounts.ISaverPlans.GICPlans;

public class AnnualInterest extends GICPlan{
    private final double  interestRate = 0.08;
    private final int periodOfMonth = 12;

    public AnnualInterest(){
        setInterestRate(interestRate);
        setPeriodOfMonth(periodOfMonth);
    }
}
