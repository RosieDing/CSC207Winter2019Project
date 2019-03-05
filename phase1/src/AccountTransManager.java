import java.util.Stack;

public class AccountTransManager {
    private Stack<Transaction> accTransList = new Stack<>();
    private int ownedAccId;

    public AccountTransManager(int ownedAccId) {
        this.ownedAccId = ownedAccId;
    }

    public Transaction getLastTrans() {
        Transaction e = accTransList.pop();
        return e;
    }

    public void addTrans(Transaction trans) {
        accTransList.push(trans);
    }
}
