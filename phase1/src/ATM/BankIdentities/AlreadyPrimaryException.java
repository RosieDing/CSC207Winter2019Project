package ATM.BankIdentities;

public class AlreadyPrimaryException extends Exception {
    public AlreadyPrimaryException() {
    }

    public AlreadyPrimaryException(String message) {
        super(message);
    }
}
