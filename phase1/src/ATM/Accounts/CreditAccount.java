package ATM.Accounts;

import ATM.InfoHandling.InfoManager;

/**
 * Credit account class
 */
public class CreditAccount extends DebtAccount {
    private String ownerID;
    private final String accountNum = "001" + ownerID + (InfoManager.getInfoManager().getAccountNum() + 1);

    /**Constructor for credit account class */
    public CreditAccount(String ownerID, double limit){
        super(ownerID, limit);
        this.ownerID = ownerID;
    }

    /**Getter method for credit account number*/
    public String getAccountNum(){
        return this.accountNum;
    }

    /**Getter method for owner id*/
    public String getOwnerID(){
        return this.ownerID;
    }


    /**To string method that will return a string combined with account type, account number, and account balance*/
    @Override
    public String toString() {
        return ("ATM.Accounts.CreditAccount" + ", "  + this.accountNum + ", " + getBalance());
    }

}