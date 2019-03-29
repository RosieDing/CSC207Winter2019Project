package ATM.Accounts;


import java.util.ArrayList;

public class ForeignCurrencyChequingAccount extends ChequingAccount{

    public ForeignCurrencyChequingAccount(ArrayList<String> ownerID, int totalNumAcc, String baseCurrency){
        super(ownerID, totalNumAcc);
        setBaseCurrency(baseCurrency);
    }

    @Override
    public String toString() {
        return  getBaseCurrency() + "Foreign Currency Chequing Account";
    }
}
