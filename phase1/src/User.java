public class User extends BankIdentity {

    private UserAccManager accManager;
    private PasswordManager passManager;

    public User() {
        String id = "";
        id = id + "020" + (Loader.getUser() + 1);
        this.setId(Integer.valueOf(id));
    }

    public void setAccManager(UserAccManager accManager) {
        this.accManager = accManager;
    }

    public void setPassManager(PasswordManager passManager) {
        this.passManager = passManager;
    }
}