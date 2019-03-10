package ATM.BankIdentities;
import ATM.Accounts.*;
import ATM.InfoHandling.InfoManager;

import java.util.*;

public class UserAccManager{
    /**
    A map that have account type as key and an arraylist of Accounts that fit the type of key.
     */
    private Map<String, ArrayList<Account>> listOfAcc = new HashMap<>();
    private String ownedUserId;
    private ChequingAccount primaryChq;

    public UserAccManager(String ownedUserId) {

        this.ownedUserId = ownedUserId;
        listOfAcc.put("TransferOutable", new ArrayList<>());
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
        InfoManager.getInfoManager().add(acc);
    }

    public Account getAccount(int accNum) throws NoSuchAccountException{
        Account result = null;
        for (ArrayList<Account> list: listOfAcc.values()) {
            for (Account acc: list) {
                if (acc.getAccountNum() == accNum) {
                    result = acc;
                    break;
                }
            }
        }
        if (result == null) {
            throw new NoSuchAccountException("Can not find this account!");
        }
        return result;
    }

    public ArrayList<Account> getAllAccounts(){
        ArrayList<Account> all = new ArrayList<>();
        for (ArrayList<Account> list: listOfAcc.values()) {
            all.addAll(list);
        }
        return all;
    }

    public ArrayList<Account> getTypeAccounts(String type) throws NoSuchTypeException{
        if (!(listOfAcc.keySet().contains(type))) {
            throw new NoSuchTypeException("You entered a wrong type!");
        }
        return listOfAcc.get(type);
    }

    public void setPrimaryChq(Account acc) throws AlreadyPrimaryException{
        if (acc instanceof ChequingAccount) {
            if (acc.getOwnerID().equals(ownedUserId)) {
                if (acc == getPrimaryChq()) {
                    throw new AlreadyPrimaryException("This account is already " +
                            "a primary chequing account.");
                } else {
                    this.primaryChq = ((ChequingAccount)acc);
                }
            }
        }
    }

    public ChequingAccount getPrimaryChq(){
        return primaryChq;
    }

    public String getDateOfCreation(String accNum){
        String result = "";
        try {
            Account a = getAccount(accNum);
            result = a.getDateOfCreation().toString();
        }catch (NoSuchAccountException e) {
            System.out.println("There is no such account");
        }
        return result;
    }

    public String getSummary(){
        StringBuilder result = new StringBuilder();
        for (String s: listOfAcc.keySet()) {
            String name = String.join(" ", s.split("(?==\\p{Upper})"));
            result.append(name).append(":\n");
            ArrayList<Account> list = listOfAcc.get(s);
            for (Account acc: list) {
                result.append(acc.toString()).append("\n");
            }
        }
        return result.toString();
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
    }

}
