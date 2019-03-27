package ATM.BankIdentities;

import ATM.Transactions.NoTransactionException;
import ATM.Transactions.TransactionManager;

public interface PrivilegeLevelA extends PrivilegeLevelB{

     public void undoAccRecentTrans(String accNum, TransactionManager trans, int times);
    public void undoUserRecentTrans(String userId, TransactionManager trans, int times);


}
