import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class UserTransManager {
    private Stack<Transaction> userTransList = new Stack<>();
    //private Map<Integer, AccountTransManager> accTransList = new HashMap();
    // deposit writer
    // pay Bill writer
    private int ownedUserId;

    public UserTransManager(int ownedUserId) {
        this.ownedUserId = ownedUserId;
    }

    // to be fixed.
    public void makeTransaction(){}

    public Transaction getUserLastTrans() {
        Transaction e = userTransList.pop();
        return e;
    }

    public void addTrans(Transaction trans){
        userTransList.push(trans);
        if (trans instanceof Deposit) {
            Loader.get(trans.getToAccNum()).getTransManager().addTrans(trans);
            // call save userTransManager
            // call save to deposit.txt
        } else {
            Loader.get(trans.getFromAccNum()).getTransManager().addTrans(trans);
            // call save userTransManager
        }

    }
}
