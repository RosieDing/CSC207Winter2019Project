package ATM.BankSystem;

import ATM.BankIdentities.AccountCreater;
import ATM.BankIdentities.BankManager;
import ATM.BankIdentities.PasswordManager;
import ATM.Machine.Money;

import java.util.EmptyStackException;

public class ManagerMenus {

    private Typer typer = new Typer();

    /**Ask the manager for an password and verify it
     * If it goes through verification, the method direct manager to the manager main menu
     */
    public void managerLog(String id) {
        BankManager bankManager = BankSystem.getInfoManager().getBankManager(id);
        PasswordManager passwordManager = bankManager.getPassManager();
        System.out.println("Welcome, our bank manager~");
        String pw = typer.promptUser("Please enter your password: ");
        passwordManager.login(pw);
        while (!passwordManager.isLogin()) {
            pw = typer.promptUser("Please re-enter your password: ");
            passwordManager.login(pw);
        }
        managerMainMenu(bankManager);
    }

    /**Displays the manager main menu to manager
     */
    public void printManagerMenu() {
        String[] list = {"Create User", "Undo Account's Most Recent Transactions",
                "Undo User's Most Recent Transactions",
                "Create an account for user", "Restock Cash Machine", "Reset Password", "Log out"};
        StringBuilder s = new StringBuilder();
        for (int i = 1; i < 8; i++) {
            s.append("Option " + i + " : " + list[i - 1] + "\n");
        }
        System.out.println(s);
    }

    /**Ask the manager to select an action within the main menu*/
    public void managerMainMenu(BankManager bankManager) {
        while (bankManager.getPassManager().isLogin()) {
            printManagerMenu();
            String chosen = typer.ensureOption(1, 7);
            switch (chosen) {
                case "1":
                    //create a new user
                    bankManager.createUser();
                    break;
                case "2":
                    //undo the last transaction of a certain account
                    undoAccountTransMenu(bankManager);
                    break;
                case "3":
                    //undo the last transaction of a certain user
                    undoUserTransMenu(bankManager);
                    break;
                case "4":
                    //create a new account
                    createAccountMenu(bankManager);
                    break;
                case "5":
                    //restock the ATM machine.
                    int numFive = typer.ensureInt("Please enter the amount of five dollars you want to restock");
                    int numTen = typer.ensureInt("Please enter the amount of ten dollars you want to restock");
                    int numTwenty = typer.ensureInt("Please enter the amount of twenty dollars you want to restock");
                    int numFifty = typer.ensureInt("Please enter the amount of fifty dollars you want to restock");
                    Money m = new Money(numFive, numTen, numTwenty, numFifty);
                    bankManager.restock(BankSystem.getInfoManager().getCashMachine(), m);
                    break;
                case "6":
                    String newPass = typer.promptUser("Please enter new password: ");
                    bankManager.getPassManager().setPassword(newPass);
                    break;
                case "7":
                    //log out
                    bankManager.getPassManager().logout();
            }
        }
    }

    /**Display the create account sub menu to allow for selection */
    public void printCreateAccountMenu() {
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
    private void createAccountMenu(BankManager bankManager) {
        printCreateAccountMenu();
        boolean stay = true;
        String chosen = typer.ensureOption(1, 5);
        if (chosen.equals("5")) {
            stay = false;
        }
        if (stay) {
            String userID = typer.ensureID();
            AccountCreater creater = new AccountCreater();
            switch (chosen) {
                case "1":
                    creater.createNewChequingAccount(userID);
                    break;
                case "2":
                    creater.createNewSavingAccount(userID);
                    break;
                case "3":
                    String limit = typer.ensureDouble("Please enter an account limit: ");
                    creater.createNewCreditAccount(userID, Double.valueOf(limit));
                    break;
                case "4":
                    String limitN = typer.ensureDouble("Please enter an account limit: ");
                    creater.createNewLineOfCredit(userID, Double.valueOf(limitN));
                    break;
            }
        }
    }


    private void undoAccountTransMenu(BankManager bankManager){
        String accNum = typer.promptUser("Please enter an account number: ");
        int times = typer.ensureInt("Please enter number of transactions needed to undo");
        try {
            for (int i = 1; i <= times; i++) {
                bankManager.undoAccMostRecentTrans(accNum);
            }
        } catch (EmptyStackException e) {
            System.out.println("No more transaction related to this account.");
        }
    }

    private void undoUserTransMenu(BankManager bankManager){
        String userId = typer.promptUser("Please enter the user id: ");
        int times = typer.ensureInt("Please enter number of transactions needed to undo");
        try {
            for (int i = 1; i <= times; i++) {
                bankManager.undoUserMostRecentTrans(userId);
            }
        } catch (EmptyStackException e) {
            System.out.println("No more transaction related to this user.");
        }
    }

}
