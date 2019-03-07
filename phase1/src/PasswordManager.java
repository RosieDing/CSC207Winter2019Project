public class PasswordManager {

    private int ownerId;
    private String password;
    private boolean islogin;

    public PasswordManager(int ownerId) {
        this.ownerId = ownerId;
    }

    private String getPassword() {
        return password;
    }

    public boolean isIslogin() {
        return islogin;
    }

    public void setPassword(String newPass) {
        if (islogin) {
            this.password = newPass;
        } else {//return not login exception
        }
    }

    public void login(String inputPass) {
        if (inputPass.equals(getPassword())) {
            islogin = true;
        }
    }

    public void logout(){
        islogin = false;
    }
}
