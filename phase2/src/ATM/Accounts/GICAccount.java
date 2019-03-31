package ATM.Accounts;

import ATM.Accounts.Plans.GICPlans.GICPlan;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class GICAccount extends InvestmentAccount {

    private String accountNum;
    private String gic_code = "005";
    private double perspectTotalInterest;
    private double perspectiveBalance;
    private Currency balance;
    private Currency availableCredit;
    private int maturityYear;
    private int maturityMonth;
    private int maturityDay;
    private int termsOfMonth;
    private int monthLeft;
    private Currency principle;

    private boolean isEnd;
   // private double principle;

    private double penalty = 0.8;

    private GICPlan plan;


    public GICAccount(ArrayList<String> ownerID, int totalNumAcc, GICPlan s, double principle, String type) {
        super(ownerID, s);
        this.principle = new Currency(type,principle);
        this.accountNum = gic_code + (totalNumAcc + 1);
        this.plan = s;
        this.termsOfMonth = plan.getPeriodMonth();
        Period p = Period.ofMonths(plan.getPeriodMonth());
        LocalDate maturityDate = getDateOfCreation().plus(p);
        this.maturityDay = maturityDate.getDayOfMonth();
        this.maturityMonth = maturityDate.getMonthValue();
        this.maturityYear = maturityDate.getYear();
        this.isEnd = plan.isEnd(maturityDate);
        this.monthLeft = plan.getMonthLeft(getDateOfCreation(), isEnd);

        perspectTotalInterest = plan.getCurrentPerspectTotalInterest(principle, getDateOfCreation(), isEnd);
        perspectiveBalance = plan.getCurrentPerspectBalance(principle,getDateOfCreation(),isEnd);

        //if (!isEnd) {
            balance = new Currency(type, perspectiveBalance);
            availableCredit = new Currency(type,principle + perspectTotalInterest * penalty);
        //} else {
          //  availableCredit = balance;
        //}
    }

    @Override
    public String getCurrencyType(){
        return balance.getType();
    }

    @Override
    public void compute(){
        if (!isEnd) {
            LocalDate maturityDate = LocalDate.of(maturityYear,maturityMonth,maturityDay);
            isEnd = plan.isEnd(maturityDate);
            perspectTotalInterest = plan.getCurrentPerspectTotalInterest(principle.getAmount(), getDateOfCreation(), isEnd);
            perspectiveBalance = plan.getCurrentPerspectBalance(principle.getAmount(),getDateOfCreation(),isEnd);
            balance.setAmount(perspectiveBalance);
            availableCredit.setAmount(principle.getAmount() + perspectTotalInterest * penalty);
        }
    }

    public boolean isEnd(){return isEnd;}

    public Currency getAvailableCredit(){
        return availableCredit;
    }


    public String getAccountNum(){
        return accountNum;
    }


    public Currency getBalance(){
        return balance;
    }


    public void setBalance(double amount){
        this.principle.setAmount( amount);
    }

    @Override
    public void transferIn(Currency amount){this.principle.add(amount);}

    public String getSummary(){return this.toString() + " , Remaining Balance: " + getBalance();}


    public Currency getNetBalance(){return balance;}

    @Override
    public String toString(){
        return "GICAccount " + this.accountNum +"with period" + termsOfMonth +
                "Maturity Date is " + maturityYear +"/" + maturityMonth +"/" +maturityDay
                + monthLeft +"months left";
    }

    @Override
    public void transferOut(Currency amount){
        if(!isEnd){
            availableCredit.subtract(amount);
            balance = availableCredit;
            isEnd = true;
        }
        else{
            balance.subtract(amount);
            availableCredit = balance;
        }
        }
}
