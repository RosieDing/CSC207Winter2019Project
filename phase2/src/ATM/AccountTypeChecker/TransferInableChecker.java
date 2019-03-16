package ATM.AccountTypeChecker;

import ATM.Accounts.Account;
import ATM.Accounts.TransferInable;

public class TransferInableChecker implements TypeChecker {

    public boolean check(Account acc){
        return (acc instanceof TransferInable);
    }
}