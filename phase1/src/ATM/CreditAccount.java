package ATM;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;

public class CreditAccount extends DebtAccount {
    private int ownerID;
    private User owner= Loader.get(ownerID);
    private String id = "001" + ownerID + String.valueOf(owner.getAccountNum + 1);
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
        return ("ATM.CreditAccount" + ", "  + this.accountNum + ", " + this.balance);
    }

}