public class PayBill extends Transaction{
    private String to;

    public PayBill(int fromAccount, String to, double amount) {
        super(amount);
        this.setFromAccNum(fromAccount);
        this.to = to;
    }

    @Override
    void begin() {
        Loader.getAccount(this.getFromAccNum()).transferOut(this.getAmount());
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
        Account acc = Loader.getAccount(this.getFromAccNum());
        int userId = acc.getOwnerID();
        return (userId + "," + this.getFromAccNum() + "," + to + ","
                + "time" + "," + this.getAmount() + "\n")
                ;
    }
}
