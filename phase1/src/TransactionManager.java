import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TransactionManager {
    private Map<Integer, Stack<Transaction>> accTransList = new HashMap<>();
    // private Map<Integer, Stack<Transaction>> userTransList = new HashMap<>();
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
        int balance = accF.getBalance();
        double amount = e.getAmount();
        if (accF instanceof TransferOutable) {
            if (accF instanceof DebtAccount) {
                possible = debtCheck((DebtAccount)accF, amount, balance);
            } else if (accF instanceof ChequingAccount) {
                possible = chequingCheck((ChequingAccount)accF, amount, balance);
            } else if (accF instanceof SacingAccount) {
                possible = savingCheck(amount, balance);
            }
        }
        return possible;
    }

    private boolean debtCheck(DebtAccount acc, double amount, int balance){
        int limit = acc.getLimit();
        return (balance + amount - limit) <= 0;
    }

    private boolean chequingCheck(ChequingAccount acc, double amount, int balance){
        int limit = acc.overDraftLimit();
        return ((balance - amount >= limit) && (balance >= 0));
    }

    private boolean savingCheck(double amount, int balance){
        return (balance - amount) >= 0;
    }

    /*public Transaction getUserLastTrans(int userId) {
        Transaction e = userTransList.get(userId).pop();
        return e;
    }*/

    public Transaction getAccLastTrans(int accNum) {
        Transaction e = accTransList.get(accNum).pop();
        return e;
    }

    /*private void addHelper(int userId, int accNum, Transaction trans) {
        accTransList.get(accNum).add(trans);
        userTransList.get(userId).add(trans);
    }*/

    public void addTrans(Transaction trans){
        if (trans.getFromAccNum() == 0) {
            //int userId = Loader.getAccount(trans.getToAccNum()).getOwnerID();
            //int accNum = trans.getToAccNum();
            accTransList.get(trans.getToAccNum()).add(trans);
            // call save TransactionManager
            // call save to deposit.txt
        } else {
            //int userId = Loader.getAccount(trans.getFromAccNum()).getOwnerID();
            //int accNum = trans.getFromAccNum();
            accTransList.get(trans.getFromAccNum()).add(trans);
            // call save userTransManager
        }

    }
}
