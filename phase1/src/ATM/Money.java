package ATM;

public class Money {
    private int numFive;
    private int numTen;
    private int numTwenty;
    private int numFifty;

    public Money(int numFive, int numTen, int numTwenty, int numFifty) {
        this.numFive = numFive;
        this.numTen = numTen;
        this.numTwenty = numTwenty;
        this.numFifty = numFifty;
    }

    public int getNumFive() {
        return numFive;
    }

    public int getNumTen() {
        return numTen;
    }

    public int getNumTwenty() {
        return numTwenty;
    }

    public int getNumFifty() {
        return numFifty;
    }
}
