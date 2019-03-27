package ATM.BankIdentities;

/** A class that represent a bank employee */
public abstract class BankEmployee extends BankIdentity{

    public abstract String getId();

    /**
     * The method for the BankEmployee to create new_user with default password "1234"
     * It will create a default primary chequing account
     * At the same time, all the information is going to be updated to global information
     */
    public void createUser() {
        AccountCreator accCreator = new AccountCreator();
        User u = new User();
        UserAccManager accM = new UserAccManager(u.getId());
        u.setAccManager(accM);
        PasswordManager passM = new PasswordManager(u.getId());
        passM.setPassword("1234");
        u.setPassManager(passM);
        System.out.println("New user created! user ID: " + u.getId()
                + " initial Password: " + "1234");
        infoManager.add(u);
        passM.addObserver(infoManager);
        accCreator.createNewChequingAccount(u.getId());
    }


}
