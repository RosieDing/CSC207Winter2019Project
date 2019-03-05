import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class UserTransManager {
    private Stack<Transaction> userTransList = new Stack<>();
    private Map<Integer, Stack<Transaction>> accTransList = new HashMap();
    // deposit writer
    // pay Bill writer
    private int ownedUserId;

    public UserTransManager(int ownedUserId) {
        this.ownedUserId = ownedUserId;

    }

    public Transaction getAccLastTrans(int accId) {
        Transaction e = accTransList.get(accId).pop();
        return e;
    }

    public Transaction getUserLastTrans() {
        Transaction e = userTransList.pop();
        return e;
    }

    public void addTrans(Transaction trans){
        userTransList.push(trans);
        if (trans instanceof Deposit) {
            accTransList.get(trans.getToAccNum()).push(trans);
            // call save userTransManager
            // call save to deposit.txt
        } else {
            accTransList.get(trans.getFromAccNum()).push(trans);
            // call save userTransManager
        }

    }
}
