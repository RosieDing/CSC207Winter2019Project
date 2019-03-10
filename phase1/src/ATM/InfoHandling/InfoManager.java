package ATM.InfoHandling;

import ATM.Accounts.Account;
import ATM.BankIdentities.BankManager;
import ATM.BankIdentities.User;
import ATM.Transactions.TransactionManager;

import java.io.*;

public class InfoManager {
    private static String filePath;
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

    public static void readFromFile(String path) {
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

    public static void saveToFile() throws IOException {

        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        // serialize the Map
        output.writeObject(infoStorer);
        output.close();
    }

    public static InfoStorer getInfoStorer() {
        return infoStorer;
    }

    public static User getUser(int id){
        String key = String.valueOf(id);
        return infoStorer.getUserMap().get(key);
    }

    public static Account getAccount(int id){
        String key = String.valueOf(id);
        return infoStorer.getAccountMap().get(key);
    }

    public static BankManager getBankManager(int id){
        String key = String.valueOf(id);
        return infoStorer.getBankManagerMap().get(key);
    }

    public TransactionManager getTransactionManager(){
        return getInfoStorer().getTransactionManager();
    }

    public static int getUserNum(){
        return infoStorer.getUserMap().size();
    }

    public static int getAccountNum(){
        return infoStorer.getAccountMap().size();
    }

    public static int getBankManagerNum(){
        return infoStorer.getBankManagerMap().size();
    }

    public static void add(User newUser){
        infoStorer.addUser(newUser);
    }

    public static void add(Account newAccount){
        infoStorer.addAccount(newAccount);
    }

    public static void add(BankManager newBankManager){
        infoStorer.addBankManager(newBankManager);
    }
}

