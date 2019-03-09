package ATM.Transactions;

import ATM.Accounts.Account;
import ATM.Accounts.TransferInable;
import ATM.Accounts.TransferOutable;
import ATM.loading.Loader;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Stack;

public class TransactionManager extends Observable {
    private Map<Integer, Stack<Transaction>> accTransList = new HashMap<>();
    private Map<Integer, Stack<Transaction>> userTransList = new HashMap<>();
    // deposit writer
    // pay Bill writer


    public void makeTransaction(Transaction e){
        if ((e.getFromAccNum()!=0) && checkFrom(e)) {
            if (e.getToAccNum()!=0){
                if (checkTo(e)){ e.begin(); }
            } else {e.begin();}
        } else if ((e instanceof Deposit) && checkTo(e)) {
            e.begin();
        }
        addTrans(e);
        //raise exception
    }

    private boolean checkTo(Transaction e){
        boolean possible = false;
        if (Loader.getAccount(e.getToAccNum()) instanceof TransferInable) {
            possible = true;
        }
        return possible;
    }

    private boolean checkFrom(Transaction e) {
        boolean possible  = false;
        Account accF = Loader.getAccount(e.getFromAccNum());
        double availableCredit = accF.getAvailableCredit();
        double amount = e.getAmount();
        if ((accF instanceof TransferOutable) && (amount <= availableCredit)) {
            possible = true;
        }
        return possible;
    }

    public Transaction getUserLastTrans(int userId) {
        Transaction e = userTransList.get(userId).pop();
        addTrans(e);
        return e;
    }

    public Transaction getAccLastTrans(int accNum) {
        Transaction e = accTransList.get(accNum).pop();
        addTrans(e);
        return e;
    }

    private void addHelper(int userId, int accNum, Transaction trans) {
        accTransList.get(accNum).add(trans);
        userTransList.get(userId).add(trans);
    }

    private void addTrans(Transaction trans){
        if (trans.getFromAccNum() == 0) {
            int userId = Loader.getAccount(trans.getToAccNum()).getOwnerID();
            int accNum = trans.getToAccNum();
            addHelper(userId, accNum, trans);
            // call save ATM.Transactions.TransactionManager
            // call save to deposit.txt
        } else {
            int userId = Loader.getAccount(trans.getFromAccNum()).getOwnerID();
            int accNum = trans.getFromAccNum();
            addHelper(userId, accNum, trans);
            // call save ATM.Transactions.TransactionManager
        }
        setChanged();
        notifyObservers();
    }
}
