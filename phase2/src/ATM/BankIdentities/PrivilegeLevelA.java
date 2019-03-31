package ATM.BankIdentities;

import ATM.Machine.CashMachine;
import ATM.Transactions.NoTransactionException;
import ATM.Transactions.TransactionManager;

public interface PrivilegeLevelA extends PrivilegeLevelB{

     public void undoAccRecentTrans(String accNum, TransactionManager trans, CashMachine machine, int times);
    public void undoUserRecentTrans(String userId, TransactionManager trans, CashMachine machine, int times);


}
