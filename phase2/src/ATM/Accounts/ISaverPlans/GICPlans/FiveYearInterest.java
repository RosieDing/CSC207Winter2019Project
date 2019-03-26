package ATM.Accounts.ISaverPlans.GICPlans;

import java.io.Serializable;

public class FiveYearInterest extends GICPlan implements Serializable {

    private final double  interestRate = 0.6;
    private final int periodOfMonth = 5*12;

    public FiveYearInterest(){
        setInterestRate(interestRate);
        setPeriodOfMonth(periodOfMonth);
    }
}
