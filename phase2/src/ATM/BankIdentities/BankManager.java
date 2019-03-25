package ATM.BankIdentities;

import ATM.Accounts.Account;
import ATM.InfoHandling.InfoManager;
import ATM.Machine.CashMachine;
import ATM.Machine.Money;
import ATM.Transactions.NoTransactionException;
import ATM.Transactions.ReverseNotPossibleException;
import ATM.Transactions.Transaction;
import ATM.Transactions.TransactionManager;

import java.util.Map;

/** A class that represent a manager of the bank
 * Help Users manage their accounts
 * Have more access than BankStaff
 */
public class BankManager extends BankEmployee {

    /** The id of this bank manager */
    private final String id;

    /** Creates a new BankManager with a password.
     * Update this new BankManager and password into the global information
     * @param totalManagerNum total number of BankManager created.
     * @param bankManagerMap a mapping from BankManager ID to the BankManager with that ID
     * @param newPassword the new password
     * @param passwordMap a mapping from BankIdentity ID to the encrypted password
     */
    public BankManager(int totalManagerNum, Map<String, BankManager> bankManagerMap,
                       String newPassword, Map<String, String> passwordMap) {
        this.id = "510" + (totalManagerNum + 1);
        bankManagerMap.put(id, this);
        setPassword(newPassword, passwordMap);
    }

    /**
     * Set the password and update this password into global information
     * @param newPassword the new password
     * @param passwordMap a mapping from BankIdentity ID to the encrypted password
     */
    private void setPassword(String newPassword, Map<String, String> passwordMap){
        PasswordManager pm = new PasswordManager(id);
        pm.setPassword(newPassword, passwordMap);
    }

    /**
     * Get the ID of the manager
     * @return the ID of the manager.
     */
    public String getId(){
        return id;
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

    /**
     * Undo the most recent transaction for the Account
     * @param accNum the AccountNumber of the account which you want to undo transaction for
     * @throws ReverseNotPossibleException
     *  */
    public void undoAccMostRecentTrans(String accNum, TransactionManager trans) throws NoTransactionException {
        try {
            Transaction e = trans.popAccLastTrans(accNum).reverse();
            // try catch where pay bill can't be reversed.
            trans.makeTransaction(e);
            // try catch if transaction cant be processed.
        } catch (ReverseNotPossibleException e) {
            System.out.println("Impossible to undo this transaction.");
        }
    }


    /** Undo the most recent transaction for the user
     *
     * @param userId the UserId of the user which you want to undo transaction for
     * @throws ReverseNotPossibleException
     *  */
    public void undoUserMostRecentTrans(String userId, TransactionManager trans) {
        try {
            Transaction e = trans.popUserLastTrans(userId).reverse();
            // try catch where pay bill can't be reversed.
            trans.makeTransaction(e);
            // try catch if transaction cant be processed.
        } catch (ReverseNotPossibleException e) {
            System.out.println("Impossible to undo this transaction.");
        } catch (NoTransactionException e) {
            System.out.println(e.getMessage());
        }
    }
}

