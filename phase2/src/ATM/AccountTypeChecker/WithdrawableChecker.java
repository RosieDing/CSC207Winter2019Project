package ATM.AccountTypeChecker;

import ATM.Accounts.Account;
import ATM.Accounts.Withdrawable;

public class WithdrawableChecker implements TypeChecker {

    public boolean check(Account acc){
        return (acc instanceof Withdrawable);
    }
}