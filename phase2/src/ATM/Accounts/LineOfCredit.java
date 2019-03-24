package ATM.Accounts;

import ATM.Accounts.TransferTypes.TransferOutable;

/**
 * a class that represent a Line of credit account
 */
public class LineOfCredit extends DebtAccount implements TransferOutable {

    /** The account number */
    private final String accountNum;

    /**
     * Constructor of line of credit account
     * Create a new line of credit account with ownerID, limit and the total number of accounts created
     *
     * @param ownerID the ID of the owner
     * @param limit the limit of this line of credit account
     * @param totalNumAcc the total number of accounts created
     */
    public LineOfCredit(String[] ownerID, int numOwner, double limit, int totalNumAcc) {
        super(ownerID, numOwner, limit);
        this.accountNum = "002" + getOwnerID() + (totalNumAcc+1);
    }

    /**Transfer money with given amount out of line of credit account
     * @param amount the given amount of money
     */
    @Override
    public void transferOut(double amount){
        setBalance(getBalance() + amount);
    }

    /**Getter for getting line of credit account number */
    public String getAccountNum(){
        return this.accountNum;
    }

    /**
     * Return a String representation of this account
     * @return a string combined with account type, account number
     */
    @Override
    public String toString() {
        return ("Line of Credit " + this.accountNum);
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