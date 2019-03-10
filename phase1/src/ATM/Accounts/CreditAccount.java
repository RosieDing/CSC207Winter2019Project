package ATM.Accounts;

import ATM.InfoHandling.InfoManager;

/**
 * Credit account class
 */
public class CreditAccount extends DebtAccount {
    private int ownerID;
    private String id = "001" + ownerID + (InfoManager.getAccountNum() + 1);
    private final String accountNum = id;

    /**Constructor for credit account class */
    public CreditAccount(int ownerID, double limit){
        super(ownerID, limit);
        this.ownerID = ownerID;
    }

    /**Getter method for credit account number*/
    public String getAccountNum(){
        return this.accountNum;
    }

    /**Getter method for owner id*/
    public int getOwnerID(){
        return this.ownerID;
    }


    /**To string method that will return a string combined with account type, account number, and account balance*/
    @Override
    public String toString() {
        return ("ATM.Accounts.CreditAccount" + ", "  + this.accountNum + ", " + getBalance());
    }

}