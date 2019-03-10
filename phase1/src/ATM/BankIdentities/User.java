package ATM.BankIdentities;

import ATM.loading.InfoManager;

public class User extends BankIdentity {

    private UserAccManager accManager;
    private PasswordManager passManager;

    public User() {
        String id = "";
        loader =InfoManager.getInfoManager();
        id = id + "020" + (loader.getUserNum() + 1);
        this.setId(Integer.valueOf(id));
    }

    public UserAccManager getAccManager() {
        return accManager;
    }

    public PasswordManager getPassManager() {
        return passManager;
    }

    public void setAccManager(UserAccManager accManager) {
        this.accManager = accManager;
    }

    public void setPassManager(PasswordManager passManager) {
        this.passManager = passManager;
    }
}
