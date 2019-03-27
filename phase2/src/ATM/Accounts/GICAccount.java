package ATM.Accounts;

import ATM.Accounts.ISaverPlans.GICPlans.GICPlan;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class GICAccount extends InvestmentAccount {

    private String accountNum;
    private String gic_code = "005";
    private double perspectTotalInterest;
    private double Balance;
    private double availableCredit;
    private int maturityYear;
    private int maturityMonth;
    private int maturityDay;
    private int termsOfMonth;
    private int monthLeft;

    private boolean isEnd;
    private double principle;

    private double penalty = 0.8;

    private GICPlan plan;

    public GICAccount(ArrayList<String> ownerID, int totalNumAcc, GICPlan s, double principle){
        super(ownerID,s);
        this.accountNum = gic_code + (totalNumAcc + 1);
        this.plan = s;
        this.principle = principle;
        this.termsOfMonth = plan.getPeriodMonth();
        Period p = Period.ofMonths(plan.getPeriodMonth());
        LocalDate maturityDate = getDateOfCreation().plus(p);
        this.maturityDay = maturityDate.getDayOfMonth();
        this.maturityMonth = maturityDate.getMonthValue();
        this.maturityYear = maturityDate.getYear();
        this.isEnd = plan.isEnd(maturityDate);
        this.monthLeft = plan.getMonthLeft(getDateOfCreation(),isEnd);
        this.Balance = plan.getCurrentPerspectBalance(principle,getDateOfCreation(),isEnd);
        perspectTotalInterest = plan.getCurrentPerspectTotalInterest(principle, getDateOfCreation(),isEnd);
        if(!isEnd){availableCredit = principle + perspectTotalInterest * penalty;}
        else{availableCredit = Balance;}
    }




    public double getAvailableCredit(){
        return availableCredit;
    }


    public String getAccountNum(){
        return accountNum;
    }


    public double getBalance(){
        return Balance;
    }


    public void setBalance(double amount){this.Balance = amount;}

    @Override
    public void transferIn(double amount){this.principle += amount;}

    public String getSummary(){return this.toString() + " , Remaining Balance: " + getBalance();}


    public double getNetBalance(){return availableCredit;}

    @Override
    public String toString(){
        return "GICAccount " + this.accountNum +"with period" + termsOfMonth +
                "Maturity Date is " + maturityYear +"/" + maturityMonth +"/" +maturityDay
                + monthLeft +"months left";
    }

    @Override
    public void transferOut(double amount){
            principle -= amount;
            isEnd = true;
        }
}
