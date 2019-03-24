package ATM.BankIdentities;

public abstract class BankEmployee  extends BankIdentity{
    private String id;

    public BankEmployee(){

    }

    public String getId(){return id;}

    /**
     * The method for the BankManger to create new_user, user_accounts_Manager
     * with new accounts.
     * And BankManger could init the PassWord of the user with "1234".
     * */
    public void createUser() {
        AccountCreator create = new AccountCreator();
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
        create.createNewChequingAccount(u.getId());


    }


}
