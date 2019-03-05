import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TransactionManager {
    private Map<Integer, Stack<Transaction>> accTransList = new HashMap<>();
    private Map<Integer, Stack<Transaction>> userTransList = new HashMap<>();
    // deposit writer
    // pay Bill writer

    // factory method to be fixed.
    public void makeTransaction(Transaction e){
        if (e.getToAccNum() != 0) {

        }
    }

    public Transaction getUserLastTrans(int userId) {
        Transaction e = userTransList.get(userId).pop();
        return e;
    }

    public Transaction getAccLastTrans(int accNum) {
        Transaction e = accTransList.get(accNum).pop();
        return e;
    }

    private void addHelper(int userId, int accNum, Transaction trans) {
        accTransList.get(accNum).add(trans);
        userTransList.get(userId).add(trans);
    }

    public void addTrans(Transaction trans){
        if (trans instanceof Deposit) {
            int userId = Loader.getAccount(trans.getToAccNum()).getOwnerID();
            int accNum = trans.getToAccNum();
            addHelper(userId, accNum, trans);
            // call save TransactionManager
            // call save to deposit.txt
        } else {
            int userId = Loader.getAccount(trans.getFromAccNum()).getOwnerID();
            int accNum = trans.getFromAccNum();
            addHelper(userId, accNum, trans);
            // call save userTransManager
        }

    }
}
