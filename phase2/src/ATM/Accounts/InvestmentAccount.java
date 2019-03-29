package ATM.Accounts;

import ATM.Accounts.Plans.Plan;
import ATM.Accounts.TransferTypes.TransferOutable;

import java.util.ArrayList;

public abstract class InvestmentAccount extends Account implements TransferOutable {
    Plan plan;

    public InvestmentAccount(ArrayList<String> ownerID, Plan s){
        super(ownerID);
        this.plan = s;
    }

    @Override
    public abstract void transferOut(double amount);
}
