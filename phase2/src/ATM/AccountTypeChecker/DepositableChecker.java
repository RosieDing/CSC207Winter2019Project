package ATM.AccountTypeChecker;

import ATM.Accounts.Account;
import ATM.Accounts.Depositable;

public class DepositableChecker implements TypeChecker {

    public boolean check(Account acc){
        return (acc instanceof Depositable);
    }
}