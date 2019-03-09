package ATM.Accounts;

import ATM.BankIdentities.User;
import ATM.loading.InfoManager;

/**
 * Credit account class
 */
public class CreditAccount extends DebtAccount {
    private int ownerID;
    private User owner= InfoManager.get(ownerID);
    private String id = "001" + ownerID + String.valueOf(owner.getAccountNum() + 1);
    private final int accountNum = Integer.valueOf(id);
    private double availableCredit = limit - balance;


    /**Constructor for credit account class */
    public CreditAccount(int ownerID, double limit){
        super(ownerID, limit, availableCredit);
    }

    /**Getter method for credit account number*/
    public int getAccountNum(){
        return this.accountNum;
    }

    /**Getter method for owner id*/
    public int getOwnerID(){
        return this.ownerID;
    }



    @Override
    public String toString() {
        return ("ATM.Accounts.CreditAccount" + ", "  + this.accountNum + ", " + getBalance());
    }

}