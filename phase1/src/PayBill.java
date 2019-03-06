public class PayBill extends Transaction{
    private final String to;
    private final int fromAccNum;
    private final int toAccNum;

    public PayBill(int fromAccount, String to, double amount) {
        super(amount);
        this.fromAccNum = fromAccount;
        this.toAccNum = 0;
        this.to = to;
    }

    public int getToAccNum() {
        return toAccNum;
    }

    public int getFromAccNum(){ return fromAccNum; }

    @Override
    void begin() {
        Loader.getAccount(this.fromAccNum).transferOut(this.getAmount());
    }

    @Override
    public Transaction reverse(){
        return null;
        //throws exception?
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        Account acc = Loader.getAccount(this.fromAccNum);
        int userId = acc.getOwnerID();
        return (userId + "," + this.fromAccNum + "," + to + ","
                + "time" + "," + this.getAmount() + "\n")
                ;
    }
}
