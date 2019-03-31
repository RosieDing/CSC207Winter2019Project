package ATM.InfoHandling;

import ATM.Accounts.Account;
import ATM.BankIdentities.*;
import ATM.Machine.CashMachine;
import ATM.Transactions.Transaction;
import ATM.Transactions.TransactionManager;

import java.io.*;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

/**
 * Managing the saving and loading of all information stored in infoStorer
 * This is an observer of password manager to make sure infoStorer is serialized every time an user log out
 */
public class InfoManager implements Observer {

    /**The default file path in which all the info is serialized and stored in*/
    private String filePath = "./serializedinfo.ser";

    /**Where all the information is stored in */
    private InfoStorer infoStorer;

    /**
     * Creates a new InfoManager that obtains infoStorer from the file in default filePath */
    public InfoManager(){
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

    /** A helper method that read the .ser file stored in path
     *  and deserialize the file into infoStorer
     *
     * @param path the path in which the .ser file is stored in
     */
    public void readFromFile(String path) {
        try {
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            //deserialize the InfoStorer
            infoStorer = (InfoStorer) input.readObject();
            addRelationship();
            input.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /** Make sure an infoManager instance can observe all the PasswordManagers */
    private void addRelationship(){
        for (User user: getInfoStorer().getUserMap().values()){
            PasswordManager pw = user.getPassManager();
            pw.addObserver(this);
        }
    }

    /** Save and serialize the infoStorer into the .ser file at the filePath*/
    public void saveToFile() {
        try {
            OutputStream file = new FileOutputStream(filePath);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);

            // serialize the InfoStorer
            output.writeObject(infoStorer);
            output.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /** @return our static infoStorer */
    public InfoStorer getInfoStorer() {
        return infoStorer;
    }

    /**@param id User ID
     * @return the User corresponding with ID id */
    public User getUser(String id){
        return infoStorer.getUserMap().get(id);
    }

    /**@param id Account number
     * @return the Account corresponding with Account number id */
    public Account getAccount(String id) throws NoSuchAccountException {
        Account acc = null;
        if (infoStorer.getAccountMap().containsKey(id)){
            acc = infoStorer.getAccountMap().get(id);
        } else {
            throw new NoSuchAccountException("There is no such an account!");
        }
        return acc;
    }

    /**@param id BankManager ID
     * @return  the User corresponding with ID id */
    public BankManager getBankManager(String id){
        return infoStorer.getBankManagerMap().get(id);
    }

    public Map<String, Account> getAccountMap(){
        return this.infoStorer.getAccountMap();
    }

    public Map<String, User> getUserMap(){
        return this.infoStorer.getUserMap();
    }

    public Map<String, BankStaff> getStaffMap(){
        return this.infoStorer.getStaffMap();
    }

    public Map<String, BankManager> getBankManagerMap(){
        return this.infoStorer.getBankManagerMap();
    }

    public Map<String, String> getAccountCreationRequest(){
        return this.infoStorer.getAccountCreationRequest();
    }

    public Map<String, String> getPasswordMap() {
        return this.infoStorer.getPasswordMap();
    }

    public Map<String, Stack<Transaction>> getAccTransMap() {
        return this.infoStorer.getAccTransMap();
    }

    public Map<String, Stack<Transaction>> getUserTransMap() {
        return this.infoStorer.getUserTransMap();
    }

    /**@return CashMachine */
    public CashMachine getCashMachine(){
        return getInfoStorer().getCashMachine();
    }

    /**
     * Get number of users already stored in infoStorer
     * @return int
     */
    public int getUserNum(){
        return getUserMap().size();
    }

    /**
     * Get number of account already stored in infoStorer.
     * @return int
     */
    public int getAccountNum(){
        return getAccountMap().size();
    }

    /**
     * Get number of bank managers already stored in infoStorer.
     * @return int
     */
    public int getBankManagerNum(){
        return getBankManagerMap().size();
    }

    /**
     * Get number of bank managers already stored in infoStorer.
     * @return int
     */
    public int getStaffNum(){
        return getStaffMap().size();
    }

    /**
     * Add a new bank manager to infoStorer.
     * @param id BankManger's id
     * @param newBankManager BankManager to be added.
     */
    public void add(String id, BankManager newBankManager){
        getBankManagerMap().put(id, newBankManager);
    }

    /**
     * Remove a request of account creation from infoStorer.
     * @param userID id of user who sent request
     * @param type type of account requested
     */
    public void removeRequest(String userID, String type) { getAccountCreationRequest().remove(userID, type); }

    /***
     * Update method. Serialize infoStorer if it is called.
     * @param o Observable item which infoManager is observing
     * @param arg Object argument
     */
    @Override
    public void update(Observable o, Object arg) {
        saveToFile();
    }
}

