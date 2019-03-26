package ATM.BankIdentities;

import ATM.Accounts.Account;
import ATM.Machine.CashMachine;
import ATM.Machine.Money;

import java.util.ArrayList;
import java.util.Map;

public interface PrivilegeLevelB {
    public void createUser(int Numuser, Map<String, User> userMap, Map<String, String> passwordMap, Map<String, ArrayList<Account>> accountListMap);
    public void restock(CashMachine machine, Money money);
}
