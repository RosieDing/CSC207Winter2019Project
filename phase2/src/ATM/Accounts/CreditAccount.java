package ATM.Accounts;

/**
 * A class that represent a credit account
 */
public class CreditAccount extends DebtAccount {

    /** The account number */
    private final String accountNum;

    /**
     * Constructor of credit account
     * Create a new credit account with ownerID and limit and the total number of accounts created
     *
     * @param ownerID the ID of the owner
     * @param limit the limit of this credit account
     * @param totalNumAcc the total number of accounts created
     */
    public CreditAccount(String[] ownerID, int numOwner, double limit, int totalNumAcc){
        super(ownerID, numOwner, limit);
        this.accountNum = "001" + getOwnerID() + (totalNumAcc+1);
    }

    /**Getter method for credit account number
     * @return account number
     */
    public String getAccountNum(){
        return this.accountNum;
    }

    /**
     * Return a String representation of this account
     * @return a string combined with account type, account number
     */
    @Override
    public String toString() {
        return ("Credit Account " + this.accountNum);
    }

    /**
     * Return the summary of this account
     * @return a String combined with account type, account number, balance
     */
    @Override
    public String getSummary() {
        return (this.toString() + " , Remaining Balance: " + getBalance());
    }

}