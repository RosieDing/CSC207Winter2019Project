package ATM;
import java.lang.reflect.Array;
import java.util.*;

public class UserAccManager extends Observable{
    private Map<String, ArrayList<Account>> listOfAcc = new HashMap<>();
    private int ownedUserId;
    private ChequingAccount primaryChq;

    public UserAccManager(int ownedUserId) {

        this.ownedUserId = ownedUserId;
        listOfAcc.put("TransferOutable", new ArrayList<>());
        //listOfAcc.put("TransferInable", new ArrayList<>());
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
        if (acc instanceof TransferOutable) {
            listOfAcc.get("TransferOutable").add(acc);
        }
        /*if (acc instanceof TransferInable) {
            listOfAcc.get("TransferInable").add(acc);
        }*/
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

    public ArrayList<Account> getAllAccounts() {
        ArrayList<Account> all = new ArrayList<>();
        for (ArrayList<Account> list: listOfAcc.values()) {
            all.addAll(list);
        }
        return all;
    }

    public ArrayList<Account> getTypeAccounts(String type) {
        return listOfAcc.get(type);
        // try catch exception
    }

    public void setPrimaryChq(Account acc){
        if (acc instanceof ChequingAccount) {
            if (acc.getOwnerID() == ownedUserId) {
                if (acc==getPrimaryChq() && getPrimaryChq()!=null) {
                    //throws exception
                } else {
                    this.primaryChq = ((ChequingAccount)acc);
                }
            }
        }
        //exceptions
    }

    public ChequingAccount getPrimaryChq(){
        return primaryChq;
        // exception
    }

    public String getDateOfCreation(int accNum){
        Account a = getAccount(accNum);
        return a.getDateOfCreation().toString();
    }

    public String getSummary(){
        StringBuilder result = new StringBuilder();
        for (String s: listOfAcc.keySet()) {
            String name = String.join(" ", s.split("(?==\\p{Upper})"));
            result.append(name + ":\n");
            ArrayList<Account> list = listOfAcc.get(s);
            for (Account acc: list) {
                result.append("account " + acc.getAccountNum()
                        + " with balance " + acc.getBalance() + "\n");
            }
        }
        return result.toString();

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
