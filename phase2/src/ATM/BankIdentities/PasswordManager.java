package ATM.BankIdentities;

import java.io.Serializable;
import java.util.Map;
import java.util.Observable;

/** A class manage operations on password of a particular user */
public class PasswordManager extends Observable implements Serializable {

    private final String ownerId;
    private boolean authority;

    /** get the id of the owner */
    public String getOwnerId() {
        return ownerId;
    }

    /** Create a new PassWordManager
     *
     * @param ownerId the Id of the passwordManager
     * */
    public PasswordManager(String ownerId) {
        this.ownerId = ownerId;
    }

    /** get the password of the password manager */
    private String getPassword(Map<String, String> passwordMap) {
        return decrypt(passwordMap.get(ownerId));
    }

    /** return the authority to confirm the login*/
    public boolean isLogin() {
        return authority;
    }

    /**
     * Encrypt the given password to a more secure String
     * @param password
     * @return encrypted password
     */
    private String encrypt(String password){
        Integer raw = Integer.valueOf(password);
        Integer id = Integer.valueOf(getOwnerId());
        return String.valueOf((raw + id) * 2 + 207);
    }

    /**
     * Decrypte the given encrypted password to its orginal form
     * @param encryptedPassword
     * @return password
     */
    private String decrypt(String encryptedPassword){
        Integer encrypted = Integer.valueOf(encryptedPassword);
        Integer id = Integer.valueOf(getOwnerId());
        return String.valueOf((encrypted - 207)/2 - id);
    }

    /** Set the password of the password Manager
     * @param newPass the new password to assign
     * */
    public void setPassword(String newPass, Map<String, String> passwordMap) {
        passwordMap.replace(getOwnerId(), encrypt(newPass));
    }

    /** Check the entering input of users with the password
     *
     * @param inputPass the entering string from user
     * */
    public void login(String inputPass, Map<String, String> passwordMap) {
        if (getPassword(passwordMap).equals(inputPass)) {
            authority = true;
        }else{
            System.out.println("Password is wrong!");
        }
    }

    /** The password Manager log out the cycle */
    public void logout(){
        authority = false;
        setChanged();
        notifyObservers();
    }
}
