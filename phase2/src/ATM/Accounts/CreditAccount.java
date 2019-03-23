package ATM.Accounts;

/**
 * Credit account class
 */
public class CreditAccount extends DebtAccount {
    private final String accountNum;

    /**
     * Constructor of credit account
     * Create a new credit account with ownerID and limit
     *
     * @param ownerID the ID of the owner
     * @param limit the limit of this credit account
     * @param totalNumAcc the total number of accounts created
     */
    public CreditAccount(String ownerID, double limit, int totalNumAcc){
        super(ownerID, limit);
        this.accountNum = "001" + getOwnerID() + String.valueOf(totalNumAcc) + 1;
    }

    /**Getter method for credit account number*/
    public String getAccountNum(){
        return this.accountNum;
    }

    /**@string a string combined with account type, account number */
    @Override
    public String toString() {
        return ("Credit Account " + this.accountNum);
    }

    /** @return a String that includes the summary of credit account: type, number, balance */
    public String getSummary() {
        return (this.toString() + " , Remaining Balance: " + getBalance());
    }

}