package ATM.AccountTypeChecker;

import ATM.Accounts.Account;
import ATM.Accounts.ChequingAccount;

public class ChequingChecker implements TypeChecker {

    public boolean check(Account acc) {
        return (acc instanceof ChequingAccount);
    }
}
