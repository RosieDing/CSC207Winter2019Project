public class RegularTrans extends Transaction {

    public RegularTrans(int fromAccNum, int toAccNum, double amount) {
        super(amount);
        this.setFromAccNum(fromAccNum);
        this.setToAccNum(toAccNum);
    }

    @Override
    public void begin(){
        Loader.getAccount(this.getFromAccNum()).transferOut(this.getAmount());
        Loader.getAccount(this.getToAccNum()).transferIn(this.getAmount());
    }

    @Override
    public Transaction reverse() {
        int toAcc = this.getFromAccNum();
        int fromAcc = this.getToAccNum();
        RegularTrans e = new RegularTrans(fromAcc, toAcc, this.getAmount());
        e.begin();
        return e;
    }
}
