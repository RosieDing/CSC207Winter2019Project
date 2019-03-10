package ATM.BankIdentities;

public class NoSuchTypeException extends Exception {
    public NoSuchTypeException() {
        super();
    }

    public NoSuchTypeException(String message) {
        super(message);
    }
}
