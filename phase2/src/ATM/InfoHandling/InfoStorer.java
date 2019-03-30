package ATM.InfoHandling;

import ATM.Accounts.Account;
import ATM.BankIdentities.BankManager;
import ATM.BankIdentities.User;
import ATM.Machine.CashMachine;
import ATM.Transactions.Transaction;
import ATM.Transactions.TransactionManager;

import java.io.Serializable;
import java.util.*;

/**
 * Managing the saving and loading of all information
 * Including accounts, users, transactions
 */
public class InfoStorer implements Serializable {
    /**A mapping of Account ID to Account */
    private Map<String, Account> accountMap;

    /**A mapping of User ID to User */
    private Map<String, User> userMap;

    /**A mapping of Bank manager ID to Bank manager*/
    private Map<String, BankManager> bankManagerMap;

    /**
     * A Map stored request of account creation.
     * Key takes userID, values are requested account type.
     */
    private Map<String, String> accountCreationRequest;

    /**
     * A Map storing the password information of every bank identities.
     * Key takes String ID, values are encrypted password
     */
    private Map<String, String> passwordMap;


    /**
     * accTrans record history of Transaction of specific accounts.
     */
    private Map<String, Stack<Transaction>> accTransMap;
    /**
     * userTransList record history of Transaction of specific users.
     */
    private Map<String, Stack<Transaction>> userTransMap;

    /**
     * CashMachine in this ATM System.
     */
    private CashMachine cashMachine;

    /**
     * Construct a infoStorer.
     */
    public InfoStorer(){
        this.accountMap = new HashMap<String, Account>();
        this.userMap = new HashMap<String, User>();
        this.bankManagerMap = new HashMap<String, BankManager>();
        this.accountCreationRequest = new HashMap<String, String>();
        this.passwordMap = new HashMap<String, String>();
        this.userTransMap = new HashMap<String, Stack<Transaction>>();
        this.accTransMap = new HashMap<String, Stack<Transaction>>();
        this.cashMachine = new CashMachine();
    }

    /**
     * Get the accountMap
     * @return accountMap
     */
    public Map<String, Account> getAccountMap() {
        return accountMap;
    }

    /**
     * Get the userMap
     * @return userMap
     */
    public Map<String, User> getUserMap() {
        return userMap;
    }

    /**
     * Get the bankManagerMap
     * @return bankManagerMap
     */
    public Map<String, BankManager> getBankManagerMap() { return bankManagerMap;}

    /**
     * Get the accountCreationRequest map
     * @return accountCreationRequest
     */
    public Map<String, String> getAccountCreationRequest() {
        return accountCreationRequest;
    }

    /**
     * Get the CashMachine
     * @return CashMachine
     */
    public CashMachine getCashMachine() {
        return cashMachine;
    }

    public Map<String, String> getPasswordMap() {
        return passwordMap;
    }

    public Map<String, Stack<Transaction>> getAccTransMap() {
        return accTransMap;
    }

    public Map<String, Stack<Transaction>> getUserTransMap() {
        return userTransMap;
    }
}
