package ATM.AccountTypeChecker;

import ATM.Accounts.Account;
import ATM.Accounts.TransferOutable;

public class TransferOutableChecker implements TypeChecker {

    public boolean check(Account acc){
        return (acc instanceof TransferOutable);
    }
}
