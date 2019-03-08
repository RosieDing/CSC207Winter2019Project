import java.util.Observable;

public class PasswordManager extends Observable {

    private final int ownerId;
    private String password;
    private boolean authority;

    public PasswordManager(int ownerId) {
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
        setChanged();
        notifyObservers();
    }

    public void login(String inputPass) {
        if (inputPass.equals(getPassword())) {
            authority = true;
        }
    }

    public void logout(){
        authority = false;
    }

    public int getOwnerId() {
        return ownerId;
    }
}
