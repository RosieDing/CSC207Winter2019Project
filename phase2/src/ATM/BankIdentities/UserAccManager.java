package ATM.BankIdentities;

import ATM.AccountTypeChecker.TypeChecker;
import ATM.Accounts.*;
import ATM.Accounts.Currency;

import java.io.Serializable;
import java.util.*;

/**
 * A class manage operations on accounts of a particular user
 */
public class UserAccManager implements Serializable, Iterable<Account> {

    /**
     * The User ID who own these accounts
     */
    private String ownerUserId;

    /**
     * The list of accounts the user owns
     */
    private Map<String, Account> accountList;


    /**
     * Create a new user account manager
     *
     * @param accountList a global mapping of User ID to list of accounts
     * @param ownerUserId the Id of the user of the userAccount manager
     */
    public UserAccManager(String ownerUserId, Map<String, Account> accountList) {
        this.accountList = accountList;
        this.ownerUserId = ownerUserId;
    }


    public void addGlobalMap(String accID, Account acc) {
        this.accountList.put(accID, acc);
    }


    /**
     * Get the list of accounts this user has
     *
     * @return the account list
     */
    public Map<String, Account> getAccountList() {
        return accountList;
    }

    /**
     * Use the account number to get the account
     *
     * @param accNum the accNum of the account we want to find
     * @throws NoSuchAccountException
     */
    public Account getAccount(String accNum) throws NoSuchAccountException {
        Iterator<Account> i = iterator();
        Account acc = null;
        while (i.hasNext()) {
            acc = i.next();
            if (acc.getAccountNum().equals(accNum)) {
                break;
            }
        }
        if (acc == null) {
            throw new NoSuchAccountException("User " + ownerUserId + " has no such an account.");
        }
        return acc;
    }

    /**
     * Return an ArrayList of account that is a specific type
     *
     * @param checker to check if account is a specific type
     * @return ArrayList of account
     */
    public ArrayList<Account> getTypeAccounts(TypeChecker checker) {
        ArrayList<Account> list = new ArrayList<>();
        for (Account acc : this) {
            if (checker.check(acc)) {
                list.add(acc);
            }
        }
        return list;
    }

    /**
     * Get the summary of all the accounts this user has
     * Will put summary of accounts that are of the same type together
     */
    public String getSummary() {
        StringBuilder result = new StringBuilder();
        Map<String, ArrayList<Account>> map = summaryHelper();
        for (String s : map.keySet()) {
            result.append(s).append(":\n");
            for (Account acc : map.get(s)) {
                result.append(acc.getSummary()).append("\n");
            }
        }
        return result.toString();
    }

    /**
     * Divide the account list into different account lists where all the accounts are of same type.
     * Return a map in which key is a account type name, and value is the account list of accounts of that type
     *
     * @return A account information map <Account Type Name, Account list of that type>
     */
    private Map<String, ArrayList<Account>> summaryHelper() {
        Map<String, ArrayList<Account>> map = new HashMap<>();
        for (Account acc : this) {
            String className = acc.getClass().getName();
            String className1 = className.replace("ATM.Accounts.", "");
            String classNameMod = String.join(" ", className1.split("(?=\\p{Upper})"));
            if (map.containsKey(classNameMod)) {
                map.get(classNameMod).add(acc);
            } else {
                ArrayList<Account> list = new ArrayList<>();
                list.add(acc);
                map.put(classNameMod, list);
            }
        }
        return map;
    }

    /**
     * Get the net total balance
     *
     * @return the net total of all the acounts
     */
    public double getNetTotal() {
        Currency sum = new Currency("CAD", 0);
        for (Account acc : this) {
            sum.add(acc.getNetBalance());
        }
        return sum.getAmount();
    }

    @Override
    /**
     * Return a iterator of accounts
     */
    public Iterator<Account> iterator() {
        return new AccountsIterator();
    }

    /**
     * A class that helps we iterate over UserAccManager
     */
    private class AccountsIterator implements Iterator<Account> {

        /**
         * Index of the account that we are iterate over
         * Initially equals 0
         */
        private int i = 0;

        /**
         * Tell the AccountsIterator whether or not there is next account
         */
        @Override
        public boolean hasNext() {
            return i < accountList.size();
        }

        /**
         * Tell the AccountsIterator which is the next account to iterate over
         */
        @Override
        public Account next() {
            if (hasNext()) {
                return accountList.get(i++);
            }
            throw new NoSuchElementException();
        }
    }

    /**
     * Send a request to tell Manager to create account
     *
     * @param type       The account type needed to be added
     * @param requestMap The global request map from User ID to the request type
     */
    public void sendRequest(String type, Map<String, String> requestMap) {
        requestMap.put(ownerUserId, type);
    }

}
