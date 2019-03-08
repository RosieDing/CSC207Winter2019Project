package ATM;

public class ChequingAccount extends AssetAccount{
    private int accountNum;
    private double balance;
    private double availableCredit;

    ChequingAccount(int ownerID){
        super(ownerID);
    }

}
