package ATM.Machine;

import ATM.InfoHandling.AlertWriter;

import java.util.Arrays;
import java.util.Observable;

public class CashMachine extends Observable {
    private int numFiveD;
    private int numTenD;
    private int numTwentyD;
    private int numFiftyD;
    private AlertWriter writer = new AlertWriter();

    public CashMachine(){}

    public void setAmount(Money money) {
        numFiveD = money.getNumFive();
        numFiftyD = money.getNumFifty();
        numTenD = money.getNumTen();
        numTwentyD = money.getNumTwenty();
        setChanged();
        notifyObservers();
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

    public Money getAmount(){
        return new Money(getNumFiveD(), getNumTenD(), getNumTwentyD(),getNumFiftyD());
    }

    public void withdrawCash(int amount) throws CashNotWithdrawableException, NotEnoughMoneyException{
        if (amount % 5 != 0) {
            throw new CashNotWithdrawableException("Amount entered should be multiple of 5.");
        }
        int[] Dbox = possibleD(amount);
        int[] DAmount = {getNumFiveD(), getNumTenD(), getNumTwentyD(), getNumFiftyD()};
        int[] Dnum = new int[Dbox.length];
        for (int i = (Dbox.length-1); i > 0; i--) {
            Dnum[i] = (amount/Dbox[i]);
            if ((DAmount[i] - Dnum[i]) >= 0) {
                amount -= (Dbox[i]*Dnum[i]);
            }
        }
        if (amount != 0) {
            throw new NotEnoughMoneyException("Not enough bills in the machine.");
        } else {
            int[] numCopy = Arrays.copyOf(Dnum, 4);
            Money m = new Money(DAmount[0] - numCopy[0], DAmount[1] - numCopy[1],
                    DAmount[2] - numCopy[2], DAmount[3] - numCopy[3]);
            setAmount(m);

        }
        checkAmount();
        /**
        need to write the warning function.
         **/
    }

    private int[] possibleD(int amount) {
        int[] doms = {5, 10, 20, 50};
        if (amount<50 && amount>=20) {
            int [] domie = Arrays.copyOf(doms, 3);
            doms = domie;
        } else if (amount < 20 && amount >= 10) {
            int [] domie = Arrays.copyOf(doms, 2);
            doms = domie;
        } else if (amount == 5) {
            int [] domie = Arrays.copyOf(doms, 1);
            doms = domie;
        }
        return doms;
    }

    public void checkAmount() {
        Money m = getAmount();
        if (m.getNumFive() < 20) { warning("Five"); }
        if (m.getNumTen() < 20) { warning("Ten"); }
        if (m.getNumTwenty() < 20) { warning("Twenty"); }
        if (m.getNumFifty() < 20) { warning("Fifty"); }
    }

    public void warning(String value){
        writer.write("Amount of "+ value + "dollar domination is under 20!");
    }
}
