package ATM.Accounts.TransferTypes;

/** the interface for the account which could pay the bills*/
public interface Payable {
    void pay(double amount);
}