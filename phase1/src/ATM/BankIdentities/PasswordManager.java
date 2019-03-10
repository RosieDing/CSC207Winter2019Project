package ATM.BankIdentities;

import java.util.Observable;

public class PasswordManager extends Observable {

    private final String ownerId;
    private String password;
    private boolean authority;
    /*
    Password will be a 4 bit numerical string
     */

    public PasswordManager(String ownerId) {
        this.ownerId = ownerId;
    }

    private String getPassword() {
        return password;
    }

    public boolean isLogin() {
        return authority;
    }

    public void setPassword(String newPass) {
        if (authority) {
            this.password = newPass;
        } else {//return not login exception
        }
    }

    public void login(String inputPass) {
        if (inputPass.equals(getPassword())) {
            authority = true;
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
