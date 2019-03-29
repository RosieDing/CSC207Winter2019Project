package ATM.Accounts;

import ATM.Accounts.TransferTypes.TransferOutable;

import java.util.ArrayList;

/**
 * An abstract class that represent an asset account
 */
public abstract class AssetAccount extends BasicAccount implements TransferOutable {

    /**
     * Account balance represents how much money is stored in this account
     */
    private double balance;

    /**
     * Constructor of asset account
     * Create a new asset account with ownerID
     * @param ownerID the ID of the owner
     */
    AssetAccount(ArrayList<String> ownerID){
           super(ownerID);
    }

    /** Returen the account balance*/
    @Override
    public double getBalance() {
        return balance;
    }

    /**Set the balance of the asset account
     * @param balance The new balance
     */
    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**Transfer in money with given amount to the asset account
     * @param amount The given amount of money
     */
    @Override
    public void transferIn(double amount) {
        balance += amount;
    }

    /**Deposit money with given amount to the asset account
     * @param amount The given amount of money
     */
    @Override
    public void deposit(double amount){
        balance += amount;
    }

    /**Transfer out money with given amount from the asset account
     * @param amount The given amount of money
     */
    @Override
    public void transferOut(double amount) {
        balance -= amount;
    }

    /**Pay money with given amount from the asset account
     * @param amount The given amount of money
     */
    @Override
    public void pay(double amount) {
        balance -= amount;
    }

    /**Withdraw money with given amount from the asset account
     * @param amount The given amount of money
     */
    @Override
    public void withdraw(double amount) {
        balance -= amount;
    }

    /**Calculating the net balance*/
    @Override
    public double getNetBalance(){
        return balance;
    }
}
