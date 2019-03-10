package ATM.Transactions;

import ATM.Accounts.Account;
import ATM.Accounts.TransferInable;
import ATM.Accounts.TransferOutable;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Stack;

public class TransactionManager extends Observable {
    private static TransactionManager m;
    private static Map<Integer, Stack<Transaction>> accTransList = new HashMap<>();
    private static Map<Integer, Stack<Transaction>> userTransList = new HashMap<>();
    // deposit writer
    // pay Bill writer

    private TransactionManager(){}

    public static TransactionManager getTransactionManager() {
        if (accTransList == null && userTransList == null) {
            TransactionManager m = new TransactionManager();
        }
        return m;
    }

    public Transaction makeTransaction(Map<String, Object> map) {
        Transaction e = null;
        switch((String)map.get("Type")) {
            case "Deposit":
                e = new Deposit((Account)map.get("toAccount"), (Double)map.get("amount"));
                break;
            case "PayBill":
                e = new PayBill((Account)map.get("fromAccount"), (String)map.get("to"),
                        (Double)map.get("amount"));
                break;
            case "Withdrawal":
                e = new Withdrawal((Account)map.get("fromAccount"), (Double)map.get("amount"));
                break;
            case "Regular":
                e = new RegularTrans((TransferOutable)map.get("fromAccount"),
                        (TransferInable)map.get("toAccount"), (Double)map.get("Amount"));
                break;
        }
        try{
            e.begin();
        } catch (TransactionAmountOverLimitException a) {
            System.out.println("Not enough balance to complete transaction.");
        } catch (NullPointerException b) {
            System.out.println("Transaction is not possible.");
        }
        return e;
    }

    public Transaction getUserLastTrans(int userId) {
        Transaction e = this.userTransList.get(userId).pop();
        addTrans(e);
        return e;
    }

    public Transaction getAccLastTrans(int accNum) {
        Transaction e = this.accTransList.get(accNum).pop();
        addTrans(e);
        return e;
    }

    private void addHelper(int userId, int accNum, Transaction trans) {
        this.accTransList.get(accNum).add(trans);
        this.userTransList.get(userId).add(trans);
    }

    private void addTrans(Transaction trans){
        if (trans.getFromAcc() == null) {
            int userId = trans.getToAcc().getOwnerID();
            int accNum = trans.getToAcc().getAccountNum();
            addHelper(userId, accNum, trans);
            // call save ATM.Transactions.TransactionManager
            // call save to deposit.txt
        } else {
            int userId = trans.getFromAcc().getOwnerID();
            int accNum = trans.getFromAcc().getAccountNum();
            addHelper(userId, accNum, trans);
            // call save ATM.Transactions.TransactionManager
        }
        setChanged();
        notifyObservers();
    }
}
