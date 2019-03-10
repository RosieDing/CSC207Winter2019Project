package ATM.BankIdentities;

public class NoSuchAccountException extends Exception {
    public NoSuchAccountException() {
        super();
    }

    public NoSuchAccountException(String message) {
        super(message);
    }
}
