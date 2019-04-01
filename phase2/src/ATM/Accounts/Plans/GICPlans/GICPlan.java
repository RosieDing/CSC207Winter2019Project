package ATM.Accounts.Plans.GICPlans;

import ATM.Accounts.Plans.Plan;
import ATM.BankSystem.Date;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public abstract class GICPlan implements Plan, Serializable {

    private double interestRate;
    private int periodOfMonth;

    @Override
    public double compute(double amount){
        return amount*(interestRate +1);
    }

    public void setPeriodOfMonth(int periodOfMonth) {
        this.periodOfMonth = periodOfMonth;
    }

    public double getInterestRate() {
        return interestRate;
    }

    protected void setInterestRate(double newInterestRate){
        this.interestRate = newInterestRate;
    }

    public double computeTotalInterest(double amount){
        return amount*interestRate;
    }

    public double computeMonthlyInterest(double amount){
        return amount*(interestRate+1)/periodOfMonth;
    }

    public int getPeriodMonth(){return periodOfMonth;}

    public double getCurrentPerspectTotalInterest(double amount, LocalDate dateOfCreation,boolean isEnd){
        if(!isEnd){
            LocalDate current = Date.getDate().getSystemCurrentTime();
            Period p = Period.between(dateOfCreation, current);
            int month = p.getMonths();
            return month * computeMonthlyInterest(amount);
        }
        else {return computeTotalInterest(amount);}
    }

    public double getCurrentPerspectBalance(double amount,LocalDate dateOfCreation, boolean isEnd){
        return amount + getCurrentPerspectTotalInterest(amount, dateOfCreation, isEnd);
    }

    public int getMonthLeft(LocalDate dateOfCreation, boolean isEnd){
        if(!isEnd){
            return periodOfMonth - getMonthpassed(dateOfCreation);
        }
        else{return 0;}
    }

    public boolean isEnd(LocalDate maturityDate){
        LocalDate current = Date.getDate().getSystemCurrentTime();
        return current.isAfter(maturityDate);
    }

    public int getMonthpassed(LocalDate dateOfCreation){
        LocalDate current = Date.getDate().getSystemCurrentTime();
        Period p = Period.between(dateOfCreation, current);
        int years = p.getYears()+1;
        int months = p.getMonths()+1;
        return (years) *12 + months;
    }

    public abstract String toString();
}
