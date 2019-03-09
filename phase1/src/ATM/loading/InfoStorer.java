package ATM.loading;

import ATM.Account;
import ATM.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Managing the saving and loading of all information
 * Including accounts, users
 */
public class InfoStorer {
    /**A mapping of Account ID to Account */
    private Map<String, Account> accountMap;

    /**A mapping of User ID to User */
    private Map<String, User> userMap;

    public InfoStorer(){
        this.accountMap = new HashMap<String, Account>();
        this.userMap = new HashMap<String, User>();
    }

    public Map<String, Account> getAccountMap() {
        return accountMap;
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }

    public void addUser(User newUser){
        this.userMap.put(String.valueOf(newUser.getId()), newUser);
    }

    public void addAccount(Account newAccount){
        this.accountMap.put(String.valueOf(newAccount.getAccountNum()), newAccount);
    }
}
