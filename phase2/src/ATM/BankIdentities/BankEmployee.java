package ATM.BankIdentities;

import ATM.Accounts.Account;
import ATM.Machine.CashMachine;
import ATM.Machine.Money;

import java.util.ArrayList;
import java.util.Map;

/** A class that represent a bank employee */
public abstract class BankEmployee extends BankIdentity{

    public abstract String getId();

    /**
     * The method for the BankEmployee to create new_user with default password "1234"
     * It will create a default primary chequing account
     * At the same time, all the information is going to be updated to global information
     */
    public void createUser(int Numuser, Map<String, User> userMap, Map<String, String> passwordMap,  Map<String, ArrayList<Account>> accountListMap) {
        AccountCreator accCreator = new AccountCreator();
        User u = new User(Numuser);
        userMap.put(u.getId(), u);
        accountListMap.put(u.getId(), null);
        PasswordManager passwordManager = new PasswordManager(u.getId());
        passwordManager.setPassword("1234", passwordMap);
        System.out.println("New user created! user ID: " + u.getId()); /* still need this?*/
        accCreator.createNewChequingAccount(Numuser, u.getId(), accountListMap);


    }


    /**
     * Restocking the CashMachine
     * @param machine the CashMachine storing cash
     * @param money a money object representing all the bills that need to be restocked into the machine
     * */
    public void restock(CashMachine machine, Money money) {
        machine.setAmount(money);
        // how to prevent other identities from touching cash machine setter?
    }


}
