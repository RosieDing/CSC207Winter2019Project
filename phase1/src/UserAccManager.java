import java.lang.reflect.Array;
import java.util.*;

public class UserAccManager extends Observable{
    private Map<String, ArrayList<Account>> listOfAcc = new HashMap<>();
    private int ownedUserId;

    public UserAccManager(int ownedUserId) {
        this.ownedUserId = ownedUserId;
    }

    public void addAccount(Account acc){
        Class c = acc.getClass();
        String name = c.getName();
        if (listOfAcc.containsKey(name)) {
            listOfAcc.get(name).add(acc);
        } else {
            ArrayList<Account> list = new ArrayList<>();
            list.add(acc);
            listOfAcc.put(name, list);
        }
        setChanged();
        notifyObservers();
    }

    public Account getAccount(int accNum) {
        for (ArrayList<Account> list: listOfAcc.values()) {
            for (Account acc: list) {
                if (acc.getAccountNum() == accNum) {
                    return acc;
                    break;
                }
            }
        }
        //throw new NoSuchElementException();
        // fix this?
    }

    public ChequingAccount getPrimaryChq(){
        ArrayList<Account> list = listOfAcc.get("ChequingAccount");
        ChequingAccount pc = null;
        for (Account acc: list) {
            if (acc.isPrimary()) {
                // Can we cancel casting?
                pc = (ChequingAccount)acc;
            }
        }
        return pc;

        // exception
    }

    public String getDateOfCreation(int accNum){
        Account a = getAccount(accNum);
        return a.getDateOfCreationg();
    }

    public String getSummary(){
        String result = "";
        for (String s: listOfAcc.keySet()) {
            String name = String.join(" ", s.split("(?==\\p{Upper})"));
            result = result + name + ":\n";
            ArrayList<Account> list = listOfAcc.get(s);
            for (Account acc: list) {
                result = result + "account " + acc.getAccountNum()
                        + " with balance " + acc.getBalance() + "\n";
            }
        }
        return result;

        // exception
    }

    public int getNetTotal(){
        int net = 0;
        for (ArrayList<Account> list: listOfAcc.values()) {
            for (Account acc: list) {
                if (acc instanceof DebtAccount) {
                    net -= acc.getBalance();
                } else if (acc instanceof AssetAccount) {
                    net += acc.getBalance();
                }
            }
        }
        return net;

        //exception
    }

}
