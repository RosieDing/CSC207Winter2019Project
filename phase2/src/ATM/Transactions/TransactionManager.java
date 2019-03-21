package ATM.Transactions;

import ATM.Accounts.Account;
import ATM.Accounts.TransferInable;
import ATM.Accounts.TransferOutable;
import ATM.Machine.CashNotWithdrawableException;
import ATM.Machine.NotEnoughMoneyException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/***
 * TransactionManager class
 */
public class TransactionManager implements Serializable {
    private static TransactionManager m;
    /***
     * accTransList record history of Transaction of specific accounts.
     */
    private Map<String, Stack<Transaction>> accTransList = new HashMap<>();
    /***
     * userTransList record history of Transaction of specific users.
     */
    private Map<String, Stack<Transaction>> userTransList = new HashMap<>();


    private TransactionManager(){}

    /***
     * Singleton pattern. get the only TransactionManager.
     * If there is not one, create a new TransactionManager.
     * @return the only TransactionManger
     */
    public static TransactionManager getTransactionManager() {
        if (m == null) {
            m = new TransactionManager();
        }
        return m;
    }

    /***
     * Take in a map recording the request from user, and make
     * corresponding transaction.
     * @param map the map recorded user's request
     * @return a corresponding new Transaction
     */
    public Transaction makeTransaction(Map<String, Object> map) {
        Transaction e = null;
        switch((String)map.get("Type")) {
            case "Deposit":
                e = new Deposit((Account)map.get("toAccount"), (Integer)map.get("amount"));
                break;
            case "PayBill":
                e = new PayBill((Account)map.get("fromAccount"), (String)map.get("to"),
                        (Double)map.get("amount"));
                break;
            case "Withdrawal":
                e = new Withdrawal((Account)map.get("fromAccount"), (Integer)map.get("amount"));
                break;
            case "Regular":
                e = new RegularTrans((TransferOutable)map.get("fromAccount"),
                        (TransferInable)map.get("toAccount"), (Double)map.get("amount"));
                break;
        }
        return makeTransaction(e);
    }

    /***
     * Overloading method. Catch possible Exceptions when making a transaction.
     * @param e Transaction
     * @return Transaction
     */
    public Transaction makeTransaction(Transaction e) {
        try{
            e.begin();
        } catch (TransactionAmountOverLimitException a) {
            System.out.println("Not enough balance to complete transaction.");
        } catch (NullPointerException b) {
            System.out.println("Transaction is not possible.");
        } catch (CashNotWithdrawableException | NotEnoughMoneyException c){
            System.out.println(c.getMessage());
        }
        if (e.isHappened()) {
            addTrans(e);
        }
        return e;
    }

    /***
     * Return the most recent transaction of a certain user.
     * @param userId id of the user
     * @return the most recent Transaction
     * @throws NoTransactionException
     */
    public Transaction viewUserLastTrans(String userId) throws NoTransactionException{
        Transaction e = popUserLastTrans(userId);
        addTrans(e);
        return e;
    }

    /***
     * Get the most recent transaction of a certain user.(without putting it back to the list)
     * @param userId id of the user
     * @return the most recent Transaction
     * @throws NoTransactionException
     */
    public Transaction popUserLastTrans(String userId) throws NoTransactionException {
        if (!userTransList.containsKey(userId)) {
            throw new NoTransactionException("No transaction with this user.");
        }
        Transaction e = userTransList.get(userId).pop();
        return e;
    }

    /***
     * Return the most recent transaction of a certain account.
     * @param accNum account number of the account
     * @return the most recent Transaction
     * @throws NoTransactionException
     */
    public Transaction viewAccLastTrans(String accNum) throws NoTransactionException {
        Transaction e = popAccLastTrans(accNum);
        addTrans(e);
        return e;
    }

    /***
     * Get the most recent transaction of a certain account.(without putting it back to the list)
     * @param accNum account number of the account
     * @return the most recent Transaction
     * @throws NoTransactionException
     */
    public Transaction popAccLastTrans(String accNum) throws NoTransactionException {
        if (!accTransList.containsKey(accNum)) {
            throw new NoTransactionException("No transaction on this account.");
        }
        Transaction e = accTransList.get(accNum).pop();
        return e;
    }

    private void accAddHelper(String accNum, Transaction trans) {
        if (accTransList.containsKey(accNum)) {
            accTransList.get(accNum).add(trans);
        } else {
            Stack a = new Stack<Transaction>();
            a.add(trans);
            accTransList.put(accNum, a);
        }
    }

    private void userAddHelper(String userId, Transaction trans) {
        if (userTransList.containsKey(userId)) {
            userTransList.get(userId).add(trans);
        } else {
            Stack a = new Stack<Transaction>();
            a.add(trans);
            userTransList.put(userId, a);
        }
    }

    private void helper(String userId, String accNum, Transaction trans) {
        accAddHelper(accNum, trans);
        userAddHelper(userId, trans);
    }

    /***
     * Add a transaction to history of transaction.
     * @param trans the Transaction to be added.
     */
    public void addTrans(Transaction trans){
        if (trans.getFromAcc() == null) {
            String userId = trans.getToAcc().getOwnerID();
            String accNum = trans.getToAcc().getAccountNum();
            helper(userId, accNum, trans);
        } else {
            String userId = trans.getFromAcc().getOwnerID();
            String accNum = trans.getFromAcc().getAccountNum();
            helper(userId, accNum, trans);

        }
    }
}
