package ATM.InfoHandling;

import ATM.Accounts.Account;
import ATM.BankIdentities.BankManager;
import ATM.BankIdentities.User;
import ATM.Transactions.TransactionManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Managing the saving and InfoHandling of all information
 * Including accounts, users, transactions
 */
public class InfoStorer {
    /**A mapping of Account ID to Account */
    private Map<String, Account> accountMap;

    /**A mapping of User ID to User */
    private Map<String, User> userMap;

    /**A mapping of Bank manager ID to Bank manager*/
    private Map<String, BankManager> bankManagerMap;

    /**A TransactionManager which has all the transaction information*/
    private TransactionManager transactionManager;

    public InfoStorer(){
        this.accountMap = new HashMap<String, Account>();
        this.userMap = new HashMap<String, User>();
        this.bankManagerMap = new HashMap<String, BankManager>();
        this.transactionManager = TransactionManager.getTransactionManager();
    }

    public Map<String, Account> getAccountMap() {
        return accountMap;
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }

    public Map<String, BankManager> getBankManagerMap() { return bankManagerMap;}

    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void addUser(User newUser){
        this.userMap.put(String.valueOf(newUser.getId()), newUser);
    }

    public void addBankManager(BankManager newBankManager){
        this.bankManagerMap.put(String.valueOf(newBankManager.getId()), newBankManager);
    }

    public void addAccount(Account newAccount){
        this.accountMap.put(String.valueOf(newAccount.getAccountNum()), newAccount);
    }
}
