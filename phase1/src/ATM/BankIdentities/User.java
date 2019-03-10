package ATM.BankIdentities;

import ATM.InfoHandling.InfoManager;

public class User extends BankIdentity {
    private final String id;
    private UserAccManager accManager;
    private PasswordManager passManager;

    public User() {
        this.id = "020" + (InfoManager.getInfoManager().getUserNum() + 1);
    }
    public String getId(){
        return id;
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

    public void sendRequest(String type){
        InfoManager.getInfoManager().add(getId(), type);
    }
}
