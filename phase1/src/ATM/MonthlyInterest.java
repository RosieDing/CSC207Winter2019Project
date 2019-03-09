package ATM;

import java.time.LocalDate;

public class MonthlyInterest implements ISaverPlan {
    double interestRate;
    LocalDate currentDate = LocalDate.now();


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

    }
}
