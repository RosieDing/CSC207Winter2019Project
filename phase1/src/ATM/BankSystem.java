package ATM;

import ATM.Accounts.Account;
import ATM.Accounts.ChequingAccount;
import ATM.BankIdentities.*;
import ATM.InfoHandling.InfoManager;
import ATM.Machine.CashMachine;
import ATM.Machine.Money;

import javax.sound.sampled.Line;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**A simulation of the banking system */
public class BankSystem {

    /**A boolean that indicates the system is turned on */
    private static boolean SystemOn = true;

    /**A singleton infoManager where are the information is handled
     * Here we deserialize all the information
     */
    private static InfoManager infoManager = InfoManager.getInfoManager();

    public static void main(String[] args) {
        // In case this is the first time that the system has ever runs (never met any user or manager before)
        // We set a default manager into the system
        if (infoManager.getBankManagerNum() == 0) {
            BankManager defaultManager = new BankManager("1234");
        }
        while (SystemOn) {
            BankSystem bs = new BankSystem();
            bs.run();
        }
    }

    /**Running the system */
    public void run() {
    }

    /**Ask whichever bankIdentity is using the system for an ID
     * identify them as User or Manager
     * and direct them to different login mechanism for checking password
     */
    public void identityLog() {
        boolean isFound = false;
        while (!isFound) {
            String id = promptUser("Please enter your ID: ");
            if (infoManager.getInfoStorer().getBankManagerMap().containsKey(id)) {
                managerLog(id);
                isFound = true;
            } else if (infoManager.getInfoStorer().getUserMap().containsKey(id)) {
                userLog(id);
                isFound = true;
            }

        }
    }

    /**Ask the manager for an password and verify it
     * If it goes through verification, the method direct manager to the manager main menu
     */
    public void managerLog(String id) {
        BankManager bankManager = infoManager.getBankManager(id);
        PasswordManager passwordManager = bankManager.getPassManager();
        System.out.println("Welcome, our bank manager~");
        String pw = promptUser("Please enter your password: ");
        passwordManager.login(pw);
        while (!passwordManager.isLogin()) {
            pw = promptUser("Please re-enter your password: ");
            passwordManager.login(pw);
        }
        managerMainMenu(bankManager);
    }

    /**Ask the user for an password and verify it
     * If it goes through verification, the method direct user to the user main menu*/
    public void userLog(String id) {
        User user = infoManager.getUser(id);
        PasswordManager passwordManager = user.getPassManager();
        System.out.println("Welcome, our customer~");
        String pw = promptUser("Please enter your password: ");
        passwordManager.login(pw);
        while (!passwordManager.isLogin()) {
            pw = promptUser("Please re-enter your password: ");
            passwordManager.login(pw);
        }
        userMainMenu(user);
    }

    /**Displays the manager main menu to manager
     */
    public void printManagerMenu() {
        String[] list = {"Create User", "Undo Account's Most Recent Transaction",
                "Create an account for user", "Restock Cash Machine", "Log out"};
        StringBuilder s = new StringBuilder();
        for (int i = 1; i < 6; i++) {
            s.append("Option " + i + " : " + list[i - 1] + "\n");
        }
        System.out.println(s);
    }

    /**Ask the manager to select an action within the main menu*/
    public void managerMainMenu(BankManager bankManager) {
        while (bankManager.getPassManager().isLogin()) {
            printManagerMenu();
            String chosen = ensureOption(1, 5);
            switch (chosen) {
                case "1":
                    //create a new user
                    bankManager.createUser();
                    break;
                case "2":
                    //undo the last transaction of a certain account
                    String accNum = promptUser("Please enter an account number: ");
                    bankManager.undoMostRecentTrans(accNum);
                    break;
                case "3":
                    //create a new account
                    managerSubMenu(bankManager);
                    break;
                case "4":
                    //restock the ATM machine.
                    int numFive = ensureInt("Please enter the amount of five dolloars you want to restock");
                    int numTen = ensureInt("Please enter the amount of ten dolloars you want to restock");
                    int numTwenty = ensureInt("Please enter the amount of twenty dolloars you want to restock");
                    int numFifty = ensureInt("Please enter the amount of fifty dolloars you want to restock");
                    Money m = new Money(numFive, numTen, numTwenty, numFifty);
                    bankManager.restock(infoManager.getCashMachine(), m);
                    break;
                case "5":
                    //log out
                    bankManager.getPassManager().logout();
            }
        }
    }

    /**Display the account sub menu to allow for selection */
    private void printManagerSubMenu() {
        String[] list = {"Chequing Account", "Saving Account",
                "Credit Account", "Line of Credit Account", "Back to previous menu"};
        StringBuilder s = new StringBuilder();
        for (int i = 1; i < 6; i++) {
            s.append("Option " + i + " : " + list[i - 1] + "\n");
        }
        System.out.println(s);
    }

    /**Sub menu for the case 3 in the manager main menu
     * Allow manager to create different types of account
     */
    private void managerSubMenu(BankManager bankManager) {
        printManagerSubMenu();
        boolean stay = true;
        String chosen = ensureOption(1, 5);
        if (chosen.equals("5")) {
            stay = false;
        }
        if (stay) {
            String userID = ensureID();
            switch (chosen) {
                case "1":
                    bankManager.createNewChequingAccount(userID);
                    break;
                case "2":
                    bankManager.createNewSavingAccount(userID);
                    break;
                case "3":
                    Double limit = ensureDouble("Please enter an account limit: ");
                    bankManager.createNewCreditAccount(userID, limit);
                    break;
                case "4":
                    Double limitN = ensureDouble("Please enter an account limit: ");
                    bankManager.createNewLineOfCredit(userID, limitN);
                    break;
            }
        }
    }

    /**Display the main menu for user */
    private void printUserMenu() {
        String[] list = {"Get All Accounts Summary", "See Net Total of Balance", "View Account",
                "Set Primary Account", "Make Transaction", "Request Creation of Account", "Reset Password", "Log Out"};
        StringBuilder s = new StringBuilder();
        for (int i = 1; i < 9; i++) {
            s.append("Option " + i + " : " + list[i - 1] + "\n");
        }
        System.out.println(s);

    }

    /**Allow user to select different action to complete */
    public void userMainMenu(User user) {
        while (user.getPassManager().isLogin()) {
            UserAccManager userAccManager = user.getAccManager();
            printUserMenu();
            String chosen = ensureOption(1, 8);
            switch (chosen) {
                case "1":
                    //get a summary of all accounts of the user
                    System.out.println(userAccManager.getSummary());
                    break;
                case "2":
                    //get a Net total of your balance
                    System.out.println(userAccManager.getNetTotal());
                    break;
                case "3":
                    //enter to a new menu that have options of all types of accounts that you have
                    userAccountInfoSubMenu(userAccManager);
                    break;
                case "4":
                    //have a menu of options of all chequing accounts that user has,
                    //set one to primary chequing account
                    userPriChqSubMenu(userAccManager);
                    break;
                case "5":
                    //enter a transaction menu to make a transaction
                    userTransSubMenu();
                case "6":
                    userReqAccSubMenu();
                    break;
                case "7":
                    System.out.println("Enter a password:");
                    String password = ensurePassword(4);
                    user.getPassManager().setPassword(password);
                    break;
                case "8":
                    user.getPassManager().logout();
                    break;
            }
        }

    }

    private void printAllAccountList(ArrayList<Account> list){
        StringBuilder s = new StringBuilder();
        for (int i = 1; i < list.size(); i++) {
            s.append("Option " + i + " : " + list.get(i-1).toString() + "\n");
        }
        s.append("Option "+ (list.size()+1) + ": Back to previous menu");
        System.out.println(s);
    }

    private void printAccountInfoSubSubMenu() {
        String[] list = {"View account balance", "View last transaction",
                "View date of creation", "Back to previous menu"};
        StringBuilder s = new StringBuilder();
        for (int i = 1; i < 4; i++) {
            s.append("Option " + i + " : " + list[i - 1] + "\n");
        }
        System.out.println(s);

    }

    private void userAccountInfoSubMenu(UserAccManager uam) {
        ArrayList<Account> list = uam.getAllAccounts();
        printAllAccountList(list);
        boolean stay = true;
        String chosen = ensureOption(1, list.size()+1);
        if (chosen.equals(String.valueOf((list.size()+1)))) {
            stay = false;
        }
        if (stay) {
            Account acc = list.get(Integer.valueOf(chosen));
            userAccountInfoSubSubMenu(acc);
        }
    }

    private void userAccountInfoSubSubMenu(Account acc){
        printAccountInfoSubSubMenu();
        boolean stay = true;
        String chosen = ensureOption(1, 4);
        if (chosen.equals(String.valueOf(4))) {
            stay = false;
        }
        if (stay) {
            switch (chosen) {
                case "1":
                    System.out.println(acc.getBalance());
                    break;
                case "2":
                    System.out.println(infoManager.getTransactionManager().getAccLastTrans(acc.getAccountNum()));
                    break;
                case "3":
                    System.out.println(acc.getDateOfCreation());
                    break;
            }

        }
    }

    private ArrayList printPriChqSubMenu(UserAccManager manager) {
        StringBuilder message = new StringBuilder();
        int length = 1;
        ArrayList acclist = new ArrayList();
        try {
            acclist = manager.getTypeAccounts("Chequing");
            length = manager.getTypeAccounts("Chequing").size();
        } catch (NoSuchTypeException e) {
            System.out.println("You do not have chequing account.");
        }
        for (int i = 1; i < length; i++) {
            message.append("Option").append(i).append(((ChequingAccount) acclist.get(i)).getAccountNum()).append("\n");
        }
        System.out.println(message);
        return acclist;
    }

    private void userPriChqSubMenu(UserAccManager manager) {
       ArrayList acclist = printPriChqSubMenu(manager);
       String chosen = ensureOption(1, acclist.size());
       try{
        manager.setPrimaryChq((ChequingAccount)acclist.get(Integer.valueOf(chosen)));}
       catch (AlreadyPrimaryException e){
           System.out.println("This is already a primary account.");
       }
    }

    private void printTransSubMenu() {

    }

    private void userTransSubMenu() {
        printTransSubMenu();
    }


    private void userReqAccSubMenu(User user){
        printManagerSubMenu();
        String chosen = ensureOption(1, 6);
        switch (chosen){
            case "1":
                user.sendRequest("Chequing Account");
            case "2":
                user.sendRequest("Saving Account");
            case "3":
                user.sendRequest("Credit Account");
            case "4":
                user.sendRequest("Line of Credit");
        }
    }


    private Double ensureDouble(String prompt) {
        boolean isEnsured = false;
        String input = "";
        while (!isEnsured) {
            input = promptUser(prompt);
            try {
                Double d = Double.valueOf(input);
                isEnsured = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Please remember to enter a number.");
            }
        }
        return Double.valueOf(input);
    }

    private int ensureInt(String prompt) {
        boolean isEnsured = false;
        String input = "";
        while (!isEnsured) {
            input = promptUser(prompt);
            try {
                Integer i = Integer.valueOf(input);
                isEnsured = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Please remember to enter an integer.");
            }
        }
        return Integer.valueOf(input);
    }

    private String ensureID() {
        boolean isEnsured = false;
        String input = "";
        while (!isEnsured) {
            input = promptUser("Please enter a user ID: ");
            if (infoManager.getInfoStorer().getUserMap().containsKey(input)) {
                isEnsured = true;
            } else {
                System.out.println("You did not enter a existing user id!");
            }
        }
        return input;
    }

    private String ensurePassword(int length) {
        boolean isEnsured = false;
        String input = "";
        while (!isEnsured) {
            input = promptUser("Please enter a password (a 4 digit integer): ");
            if (input.length() == length) {
                try {
                    Integer i = Integer.valueOf(input);
                    isEnsured = true;
                } catch (NumberFormatException nfe) {
                    System.out.println("Please remember to enter a integer!");
                }
            } else {
                System.out.println("Please remember to enter a 4 digit integer!");
            }
        }
        return input;
    }

    private String ensureOption(int min, int max) {
        boolean isEnsured = false;
        String chose = "";
        while (!isEnsured) {
            chose = promptUser("Please enter an integer ranging from " + min + " to " + max + ":");
            try {
                Integer i = Integer.valueOf(chose);
                if (min <= i && i <= max) {
                    isEnsured = true;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Please remember to enter an integer.");
            }
        }
        return chose;
    }

    private String promptUser(String prompt) {
        BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(prompt);
        try {
            String input = kbd.readLine();
            kbd.close();
            return input;
        } catch (IOException ex) {
            return ex.toString();
        }
    }
}
