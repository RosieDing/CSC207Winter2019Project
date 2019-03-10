package ATM.BankIdentities;

import java.io.Serializable;
import java.util.Observable;

/** The passWordManager class */
public class PasswordManager extends Observable implements Serializable {

    private final String ownerId;
    private String password;
    private boolean authority;

    /** Create a new PassWordManager
     *
     * @param ownerId the Id of the passwordManager
     * */
    public PasswordManager(String ownerId) {
        this.ownerId = ownerId;
    }

    /** get the password of the password manager */
    private String getPassword() {
        return password;
    }

    /** return the authority */
    public boolean isLogin() {
        return authority;
    }

    public void setPassword(String newPass) {
        if (authority) {
            this.password = newPass;
        } else {
            System.out.println("You don't have authority to modify this.");
        }
    }

    public void login(String inputPass) {
        if (inputPass.equals(getPassword())) {
            authority = true;
        }else{
            System.out.println("Password is wrong!");
        }
    }

    public void logout(){
        authority = false;
        setChanged();
        notifyObservers();
    }

    public String getOwnerId() {
        return ownerId;
    }
}
