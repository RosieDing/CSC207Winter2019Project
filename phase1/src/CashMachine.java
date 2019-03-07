public class CashMachine {
    private int numFiveD;
    private int numTenD;
    private int numTwentyD;
    private int numFiftyD;

    public CashMachine(){}

    void setAmount(Money money) {
        numFiveD = money.getNumFive();
        numFiftyD = money.getNumFifty();
        numTenD = money.getNumTen();
        numTwentyD = money.getNumTwenty();
    }

    public int getNumFiveD() {
        return numFiveD;
    }

    public int getNumTenD() {
        return numTenD;
    }

    public int getNumTwentyD() {
        return numTwentyD;
    }

    public int getNumFiftyD() {
        return numFiftyD;
    }

    public void getAmount(){
        System.out.println("Amount of $5: " + getNumFiveD());
        System.out.println("Amount of $10: " + getNumTenD());
        System.out.println("Amount of $20: " + getNumTwentyD());
        System.out.println("Amount of $50: " + getNumFiftyD());
    }

}
