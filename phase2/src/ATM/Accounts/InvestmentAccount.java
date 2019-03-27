package ATM.Accounts;

import ATM.Accounts.ISaverPlans.ISaverPlan;
import ATM.Accounts.TransferTypes.TransferOutable;

import java.util.ArrayList;

public abstract class InvestmentAccount extends Account implements TransferOutable {
    ISaverPlan plan;

    public InvestmentAccount(ArrayList<String> ownerID, ISaverPlan s){
        super(ownerID);
        this.plan = s;
    }

    @Override
    public abstract void transferOut(double amount);
}
