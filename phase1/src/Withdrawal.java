public class Withdrawal extends Transaction {
    private final int fromAccNum;
    private final int toAccNum;

    public Withdrawal(int fromAccount, double amount) {
        super(amount);
        this.fromAccNum = fromAccount;
        this.toAccNum = 0;
    }

    public int getToAccNum() {
        return toAccNum;
    }

    public int getFromAccNum() {
        return fromAccNum;
    }

    @Override
    void begin() {
        Loader.getAccount(getFromAccNum()).transferOut(this.getAmount());
    }

    @Override
    public Deposit reverse() {
        int toAcc = getFromAccNum();
        return new Deposit(toAcc, this.getAmount());
    }

    @Override
    public boolean selfCheck() {
        boolean possible  = false;
        Account acc = Loader.getAccount(getFromAccNum());
        int balance = acc.getBalance();
        if (acc instanceof TransferOutable) {
            if (acc instanceof DebtAccount) {
                possible = debtCheck((DebtAccount)acc, getAmount(), balance);
            } else if (acc instanceof ChequingAccount) {
                possible = chequingCheck((ChequingAccount)acc, getAmount(), balance);
            } else if (acc instanceof SacingAccount) {
                possible = savingCheck(getAmount(), balance);
            }
        }
        return possible;
    }

    private boolean debtCheck(DebtAccount acc, double amount, int balance){
        int limit = acc.getLimit();
        return (balance + amount - limit) <= 0;
    }

    private boolean chequingCheck(ChequingAccount acc, double amount, int balance){
        int limit = acc.overDraftLimit();
        return ((balance - amount >= limit) && (balance >= 0));
    }

    private boolean savingCheck(double amount, int balance){
        return (balance - amount) >= 0;
    }
}
