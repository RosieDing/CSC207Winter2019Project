package ATM.InfoHandling;

import ATM.Accounts.Account;
import ATM.BankIdentities.BankManager;
import ATM.BankIdentities.User;
import ATM.Transactions.TransactionManager;

import java.io.*;

public class InfoManager {
    private static String filePath = "./serializedinfo.ser";
    private static InfoStorer infoStorer;
    private static InfoManager infoManager;

    private InfoManager(){
        infoStorer = new InfoStorer();
        File file = new File(filePath);
        if (file.exists()) {
            readFromFile(filePath);
        } else {
            try {
                file.createNewFile();
            } catch (Exception ex) {
                System.out.println(ex);;
            }
        }
    }

    public static InfoManager getInfoManager(){
        if (infoManager == null){
            infoManager = new InfoManager();
        }
        return infoManager;
    }

    public void readFromFile(String path) {
        try {
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            //deserialize the InfoStorer
            infoStorer = (InfoStorer) input.readObject();
            input.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void saveToFile() throws IOException {

        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        // serialize the Map
        output.writeObject(infoStorer);
        output.close();
    }

    public InfoStorer getInfoStorer() {
        return infoStorer;
    }

    public User getUser(String s){
        return infoStorer.getUserMap().get(s);
    }

    public Account getAccount(String s){
        return infoStorer.getAccountMap().get(s);
    }

    public BankManager getBankManager(String s){
        return infoStorer.getBankManagerMap().get(s);
    }

    public TransactionManager getTransactionManager(){
        return getInfoStorer().getTransactionManager();
    }

    public int getUserNum(){
        return infoStorer.getUserMap().size();
    }

    public int getAccountNum(){
        return infoStorer.getAccountMap().size();
    }

    public int getBankManagerNum(){
        return infoStorer.getBankManagerMap().size();
    }

    public void add(User newUser){
        infoStorer.addUser(newUser);
    }

    public void add(Account newAccount){
        infoStorer.addAccount(newAccount);
    }

    public void add(BankManager newBankManager){
        infoStorer.addBankManager(newBankManager);
    }
}

