package ATM.Transactions;

import ATM.Accounts.Account;
import ATM.Accounts.TransferInable;
import ATM.Accounts.TransferOutable;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TransactionManager{
    private static TransactionManager m;
    private Map<String, Stack<Transaction>> accTransList = new HashMap<>();
    private Map<String, Stack<Transaction>> userTransList = new HashMap<>();


    private TransactionManager(){}

    public static TransactionManager getTransactionManager() {
        if (m == null) {
            m = new TransactionManager();
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

    public Transaction makeTransaction(Transaction e) {
        try{
            e.begin();
        } catch (TransactionAmountOverLimitException a) {
            System.out.println("Not enough balance to complete transaction.");
        } catch (NullPointerException b) {
            System.out.println("Transaction is not possible.");
        }
        return e;
    }

    public Transaction getUserLastTrans(String userId) {
        Transaction e = userTransList.get(userId).pop();
        addTrans(e);
        return e;
    }

    public Transaction getAccLastTrans(String accNum) {
        Transaction e = accTransList.get(accNum).pop();
        addTrans(e);
        return e;
    }

    private void addHelper(String userId, String accNum, Transaction trans) {
        accTransList.get(accNum).add(trans);
        userTransList.get(userId).add(trans);
    }

    public void addTrans(Transaction trans){
        if (trans.getFromAcc() == null) {
            String userId = trans.getToAcc().getOwnerID();
            String accNum = trans.getToAcc().getAccountNum();
            addHelper(userId, accNum, trans);
        } else {
            String userId = trans.getFromAcc().getOwnerID();
            String accNum = trans.getFromAcc().getAccountNum();
            addHelper(userId, accNum, trans);

        }
    }
}
