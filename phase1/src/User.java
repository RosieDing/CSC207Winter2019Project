public class User extends BankIdentity {

    private UserAccManager accManager;
    private PasswordManager passManager;

    public User() {
        String id = "";
        id = id + "020" + (Loader.getUser() + 1);
        this.setId(Integer.valueOf(id));

        UserAccManager accM = new UserAccManager(getId());
        PasswordManager passM = new PasswordManager(getId());
        setAccManager(accM);
        setPassManager(passM);
    }

    public void setAccManager(UserAccManager accManager) {
        this.accManager = accManager;
    }

    public void setPassManager(PasswordManager passManager) {
        this.passManager = passManager;
    }
}
