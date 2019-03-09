package ATM.loading;

import ATM.Account;
import ATM.User;

import java.io.*;

public class Loader {
    private static InfoStorer info;

    private static Loader loader;

    private Loader(String filePath) throws ClassNotFoundException{
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

    public static Loader getLoader(String filePath) throws ClassNotFoundException{
        if (loader == null){
            loader = new Loader(filePath);
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

    public void saveToFile(String filePath) throws IOException {

        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        // serialize the Map
        output.writeObject(info);
        output.close();
    }

    public InfoStorer getInfo() {
        return info;
    }

    public User getUser(int id){
        String key = String.valueOf(id);
        return info.getUserMap().get(key);
    }

    public Account getAccount(int id){
        String key = String.valueOf(id);
        return info.getAccountMap().get(key);
    }

    public int getUserNum(){
        return info.getUserMap().size();
    }

    public int getAccountNum(){
        return info.getAccountMap().size();
    }

    public void add(User newUser){
        info.addUser(newUser);
    }

    public void add(Account newAccount){
        info.addAccount(newAccount);
    }
}

