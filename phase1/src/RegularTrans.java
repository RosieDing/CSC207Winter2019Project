public class RegularTrans extends Transaction {
    private final int fromAccNum;
    private final int toAccNum;

    public RegularTrans(int fromAccNum, int toAccNum, double amount) {
        super(amount);
        this.fromAccNum = fromAccNum;
        this.toAccNum = toAccNum;
    }

    public int getFromAccNum() {
        return fromAccNum;
    }

    public int getToAccNum() {
        return toAccNum;
    }

    @Override
    void begin(){
        Loader.getAccount(this.fromAccNum).transferOut(this.getAmount());
        Loader.getAccount(this.toAccNum).transferIn(this.getAmount());
    }

    @Override
    public Transaction reverse() {
        int toAcc = this.fromAccNum;
        int fromAcc = this.toAccNum;
        return new RegularTrans(fromAcc, toAcc, this.getAmount());
    }
}
