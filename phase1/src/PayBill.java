public class PayBill extends Transaction{
    private String to;

    public PayBill(int fromAccount, String to, double amount) {
        super(amount);
        this.setFromAccNum(fromAccount);
        this.to = to;
    }

    @Override
    public void begin() {
        Loader.getAccount(this.getFromAccNum()).transferOut(this.getAmount());
    }

    @Override
    public Transaction reverse(){
        return null;
    }

}
