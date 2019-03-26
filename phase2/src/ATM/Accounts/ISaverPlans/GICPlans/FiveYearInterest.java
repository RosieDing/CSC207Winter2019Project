package ATM.Accounts.ISaverPlans.GICPlans;

import java.io.Serializable;

public class FiveYearInterest extends GICPlan implements Serializable {

    private final double  interestRate = 0.6;
    private final int periodOfMonth = 5*12;

    public FiveYearInterest(){
        setInterestRate(interestRate);
        setPeriodOfMonth(periodOfMonth);
    }
//    @Override
//    public double compute(double amount){
//        return amount*(interestRate +1);
//    }
//
//    public double computeTotalInterest(double amount){
//        return amount*interestRate;
//    }
//
//    public double computeMonthlyInterest(double amount){
//        return amount*(interestRate+1)/periodOfMonth;
//    }
//
//    public int getPeriodMonth(){return periodOfMonth;}
//
//    public double getPerspectTotalInterest(double amount, LocalDate dateOfCreation,boolean isEnd){
//        if(!isEnd){
//            Period p = Period.between(dateOfCreation, current);
//            int month = p.getMonths() + 1;
//            return month * computeMonthlyInterest(amount);
//        }
//        else {return computeTotalInterest(amount);}
//    }
//
//    public double getPerspectBalance(double amount,LocalDate dateOfCreation, boolean isEnd){
//        return amount + getPerspectTotalInterest(amount, dateOfCreation, isEnd);
//    }
//
//    public int getMonthLeft(LocalDate dateOfCreation, boolean isEnd){
//        if(!isEnd){
//            return periodOfMonth - getMonthpassed(dateOfCreation);
//        }
//        else{return 0;}
//    }

}
