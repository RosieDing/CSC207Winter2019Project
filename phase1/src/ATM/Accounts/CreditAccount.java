package ATM.Accounts;

import ATM.BankIdentities.User;
import ATM.loading.Loader;

public class CreditAccount extends DebtAccount {
    private int ownerID;
    private User owner= Loader.get(ownerID);
    private String id = "001" + ownerID + String.valueOf(owner.getAccountNum() + 1);
    private final int accountNum = Integer.valueOf(id);


    public CreditAccount(int ownerID, double limit){
        super(ownerID, limit);
        this.ownerID = ownerID;
    }


    public int getAccountNum(){
        return this.accountNum;
    }
    public int getOwnerID(){
        return this.ownerID;
    }



    @Override
    public String toString() {
        return ("ATM.Accounts.CreditAccount" + ", "  + this.accountNum + ", " + getBalance());
    }

}