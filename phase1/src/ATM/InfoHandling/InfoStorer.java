package ATM.InfoHandling;

import ATM.Accounts.Account;
import ATM.BankIdentities.BankManager;
import ATM.BankIdentities.User;
import ATM.Machine.CashMachine;
import ATM.Transactions.TransactionManager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Managing the saving and loading of all information
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

    private Map<String, String> accountCreationRequest;

    private CashMachine cashMachine;

    public InfoStorer(){
        this.accountMap = new HashMap<String, Account>();
        this.userMap = new HashMap<String, User>();
        this.bankManagerMap = new HashMap<String, BankManager>();
        this.transactionManager = TransactionManager.getTransactionManager();
        this.accountCreationRequest = new HashMap<>();
        this.cashMachine = new CashMachine();
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

    public Map<String, String> getAccountCreationRequest() {
        return accountCreationRequest;
    }

    public CashMachine getCashMachine() {
        return cashMachine;
    }

    public void addUser(User newUser){
        this.userMap.put(newUser.getId(), newUser);
    }

    public void addBankManager(BankManager newBankManager){
        this.bankManagerMap.put(newBankManager.getId(), newBankManager);
    }

    public void addAccount(Account newAccount){
        this.accountMap.put(newAccount.getAccountNum(), newAccount);
    }
}
