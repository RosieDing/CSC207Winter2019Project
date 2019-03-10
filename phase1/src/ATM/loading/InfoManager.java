package ATM.loading;

import ATM.Accounts.Account;
import ATM.BankIdentities.User;

import java.io.*;

public class InfoManager {
    private static String filePath;
    private static InfoStorer info;
    private static InfoManager loader;

    private InfoManager() throws ClassNotFoundException{
        info = new InfoStorer();
        File file = new File(filePath);
        if (file.exists()) {
            readFromFile(filePath);
        } else {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.out.println(ex);;
            }
        }
    }

    public static InfoManager getInfoManager() throws ClassNotFoundException{
        if (loader == null){
            loader = new InfoManager();
        }
        return loader;
    }

    public static void readFromFile(String path) throws ClassNotFoundException {
        try {
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            //deserialize the InfoStorer
            info = (InfoStorer) input.readObject();
            input.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public static void saveToFile() throws IOException {

        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        // serialize the Map
        output.writeObject(info);
        output.close();
    }

    public static InfoStorer getInfo() {
        return info;
    }

    public static User getUser(int id){
        String key = String.valueOf(id);
        return info.getUserMap().get(key);
    }

    public static Account getAccount(int id){
        String key = String.valueOf(id);
        return info.getAccountMap().get(key);
    }

    public static int getUserNum(){
        return info.getUserMap().size();
    }

    public static int getAccountNum(){
        return info.getAccountMap().size();
    }

    public static void add(User newUser){
        info.addUser(newUser);
    }

    public static void add(Account newAccount){
        info.addAccount(newAccount);
    }
}

