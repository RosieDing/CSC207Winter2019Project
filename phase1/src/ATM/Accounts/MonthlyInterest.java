package ATM.Accounts;

import java.time.LocalDate;
/**
 * The plan with monthly interest 1%
 */

public class MonthlyInterest implements ISaverPlan {
    private double interestRate;
    private LocalDate currentDate = LocalDate.now();


    public MonthlyInterest(double interestRate){
        this.interestRate = interestRate;
    }

    @Override
    public double compute(double amount) {
        if (currentDate.getDayOfMonth() == 1){
                return amount * interestRate;
            }
            return amount;
    }

    String ToString(){
        return "This is a SavingPlan with monthly interest"+interestRate;
    }

    }

