package ATM.AccountTypeChecker;

import ATM.Accounts.Account;
import ATM.Accounts.Payable;

public class PayableChecker implements TypeChecker {

    public boolean check(Account acc){
        return (acc instanceof Payable);
    }
}