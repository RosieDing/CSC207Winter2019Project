package ATM.BankSystem;

import ATM.AccountTypeChecker.*;
import ATM.Accounts.Account;
import ATM.Accounts.ChequingAccount;
import ATM.Accounts.CreditAccount;
import ATM.BankIdentities.*;
import ATM.InfoHandling.InfoManager;
import ATM.Machine.CashNotWithdrawableException;
import ATM.Machine.NotEnoughMoneyException;
import ATM.Transactions.NoTransactionException;
import ATM.Transactions.Transaction;
import ATM.Transactions.TransactionManager;
import ATM.Transactions.Withdrawal;

import javax.sound.sampled.Line;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserMenus {

    private Typer typer = new Typer();

    /**Ask the user for an password and verify it
     * If it goes through verification, the method direct user to the user main menu*/
    public void userLog(String id) {
        User user = BankSystem.getInfoManager().getUser(id);
        PasswordManager passwordManager = user.getPassManager();
        System.out.println("Welcome, our customer~");
        String pw = typer.promptUser("Please enter your password: ");
        passwordManager.login(pw);
        while (!passwordManager.isLogin()) {
            pw = typer.promptUser("Please re-enter your password: ");
            passwordManager.login(pw);
        }
        userMainMenu(user);
    }


    /**Display the main menu for user */
    private void printFixedMenu(String[] list) {
        StringBuilder s = new StringBuilder();
        for (int i = 1; i < (list.length + 1); i++) {
            s.append("Option " + i + " : " + list[i - 1] + "\n");
        }
        System.out.println(s);

    }

    /**Allow user to select different action to complete */
    public void userMainMenu(User user) {
        while (user.getPassManager().isLogin()) {
            UserAccManager userAccManager = user.getAccManager();
            String[] list = {"Get All Accounts Summary", "See Net Total of Balance", "View Account",
                    "Set Primary Account", "Make Transaction", "Request Creation of Account", "Reset Password",
                    "Log Out"};
            printFixedMenu(list);
            String chosen = typer.ensureOption(1, 8);
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
                    userTransSubMenu(userAccManager);
                    break;
                case "6":
                    userReqAccSubMenu(user);
                    break;
                case "7":
                    System.out.println("Enter a password:");
                    String password = typer.ensurePassword(4);
                    user.getPassManager().setPassword(password);
                    break;
                case "8":
                    user.getPassManager().logout();
                    break;
            }
        }

    }

    /***
     * A String used in following print menu methods.
     */
    String back = "Back to previous menu";

    /** print all the available choice between the accounts*/
    private void printAllAccountList(ArrayList<Account> list){
        StringBuilder s = new StringBuilder();
        for (int i = 1; i <= list.size(); i++) {
            s.append("Option " + i + " : " + list.get(i-1).toString() + "\n");
        }
        s.append("Option "+ (list.size()+1) + ": " + back);
        System.out.println(s);
    }


    /***
     * Menu Option 3: Print account info
     */

    /** Using the recursion when to provide the print All Account list choice*/
    private void userAccountInfoSubMenu(UserAccManager uam) {
        ArrayList<Account> list = uam.getListOfAcc();
        printAllAccountList(list);
        boolean stay = true;
        String chosen = typer.ensureOption(1, list.size()+1);
        if (chosen.equals(String.valueOf((list.size()+1)))) {
            stay = false;
        }
        if (stay) {
            Account acc = list.get(Integer.valueOf(chosen)-1);
            userAccountInfoSubSubMenu(acc);
        }
    }

    /** Depend on the customer's choice show the information under their accounts*/
    private void userAccountInfoSubSubMenu(Account acc){
        boolean stay = true;
        while (stay) {
            String[] list = {"View account balance", "View last transaction",
                    "View date of creation", back};
            printFixedMenu(list);
            String chosen = typer.ensureOption(1, 4);
            switch (chosen) {
                case "1":
                    System.out.println(acc.getBalance());
                    break;
                case "2":
                    try {
                        System.out.println(BankSystem.getInfoManager().getTransactionManager().viewAccLastTrans(acc.getAccountNum()));
                    } catch (NoTransactionException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "3":
                    System.out.println(acc.getDateOfCreation());
                    break;
                case "4":
                    stay = false;
                    break;
            }

        }
    }


    /***
     * Menu Option 4: Set Primary Chequing Account
     * @param manager
     * @return
     */

    /** Print all the information under the chequing account*/
    private ArrayList printPriChqSubMenu(UserAccManager manager) {
        StringBuilder message = new StringBuilder();
        int length = 1;
        ArrayList accList = manager.getTypeAccounts(new ChequingChecker());
        length = accList.size();
        for (int i = 1; i <= length; i++) {
            message.append("Option ").append(i).append(" : ").append(((ChequingAccount) accList.get(i-1)).toString()).append("\n");
        }
        System.out.println(message);
        return accList;
    }

    /** Manager setting the selected chequing account as primary*/
    private void userPriChqSubMenu(UserAccManager manager) {
        ArrayList accList = printPriChqSubMenu(manager);
        String chosen = typer.ensureOption(1, accList.size());
        try{
            manager.setPrimaryChq((ChequingAccount)accList.get(Integer.valueOf(chosen) - 1));}
        catch (AlreadyPrimaryException e){
            System.out.println("This is already a primary account.");
        }
    }





    /***
     * Menu option 5: Make transaction
     */

    String noMore = "I don't want to make this transaction";

    /** Start a transaction by choosing type of transaction*/
    private void userTransSubMenu(UserAccManager uam) {
        boolean stay = true;
        while (stay) {
            String[] list = {"Regular Transaction", "Deposit",
                    "Withdrawal", "Pay Bills", back};
            printFixedMenu(list);
            String chosen = typer.ensureOption(1, 5);
            switch (chosen) {
                case "1":
                    regularTransactionMenu(uam);
                    break;
                case "2":
                    depositMenu(uam);
                    break;
                case "3":
                    withdrawalMenu(uam);
                    break;
                case "4":
                    payBillMenu(uam);
                    break;
                case "5":
                    stay = false;
            }

        }
    }

    /***
     * Print a helper menu
     */
    private void printTransHelperOneMenu() {
        String[] list = {"Continue", back};
        StringBuilder s = new StringBuilder();
        for (int i = 1; i <= 2; i++) {
            s.append("Option " + i + " : " + list[i - 1] + "\n");
        }
        System.out.println(s.toString());
    }

    /***
     * Helper method that allow user to choose if they want to continue to make the transaction or not.
     * @param map recording information of this transaction
     */
    private Transaction transactionHelperOne(Map<String, Object> map){
        boolean stay = true;
        printTransHelperOneMenu();
        String chosen = typer.ensureOption(1, 2);
        Transaction e = null;
        if (chosen.equals("2")) {
            stay = false;
        }
        if (stay) {
            if (e instanceof Withdrawal) {
                try {
                    InfoManager.getInfoManager().getCashMachine().withdrawCash(((Withdrawal)e).getAmount());}
                catch (CashNotWithdrawableException e1) {
                    System.out.println(e1);
                } catch (NotEnoughMoneyException e2) {
                    System.out.println(e2);
                }
            }
            TransactionManager tm = BankSystem.getInfoManager().getTransactionManager();
            e = tm.makeTransaction(map);
        }
        return e;
    }

    /***
     * Print a specific type of accounts as a selective menu
     * @param list
     */
    private void printGetTypeAccountMenu(ArrayList<Account> list){
        int j = 1;
        for (Account acc: list) {
            System.out.println("Option "+ j + ": " +acc.getSummary());
            j++;
        }
        System.out.println("Option " + j + ": " + noMore);
    }

    /***
     * Return a TypeChecker depending on the input String
     * @param type String specifying which TypeChecker is wanted
     * @return TypeChecker
     */
    private TypeChecker typeCheckerPicker(String type) {
        TypeChecker a = null;
        switch (type){
            case "TransferInable":
                System.out.println("Please choose account you want to transfer money in to:");
                a =  new TransferInableChecker();
                break;
            case "TransferOutable":
                System.out.println("Please choose account you want to transfer money out from:");
                a =  new TransferOutableChecker();
                break;
            case "Payable":
                System.out.println("Please choose account you will use to pay the bill:");
                a =  new PayableChecker();
                break;
            case "Withdrawable":
                System.out.println("Please choose account you want to withdraw money from:");
                a = new WithdrawableChecker();
                break;
            case "Depositable":
                a = new DepositableChecker();
        }
        return a;
    }

    /***
     * Allow user to select an account of a specific type
     * @param uam UserAccountManager of that user
     * @param type String specifying the type of accounts
     * @return Account chosen
     */
    private Account getTypeAccountMenu(UserAccManager uam, String type){
        Account acc = null;
        ArrayList<Account> list = uam.getTypeAccounts(typeCheckerPicker(type));
        printGetTypeAccountMenu(list);
        acc = null;
        boolean stay = true;
        String chosen = typer.ensureOption(1, list.size() + 1);
        if (chosen.equals(String.valueOf(list.size() + 1))) {
            stay = false;
        }
        if (stay) {
            acc = list.get(Integer.valueOf(chosen) - 1);
        }
        return acc;
    }

    /***
     * Get the amount of transaction from keyboard
     * @param map recording information of transaction
     */
    private Transaction getDoubleAmountMenu(Map<String, Object> map) {
        boolean stay = true;
        String amount = typer.ensureDouble("Please enter the amount of money (Enter '0' if you want to go " +
                "back to previous menu):");
        Transaction e = null;
        if (amount.equals("0")) {
            stay = false;
        }
        if (stay) {
            map.put("amount", Double.valueOf(amount));
            e = transactionHelperOne(map);
        }
        return e;
    }

    private Transaction getIntAmountMenu(Map<String, Object> map) {
        boolean stay = true;
        int amount = 0;
        amount = typer.ensureInt("Please enter the amount of money (Enter '0' if you want to go " +
                    "back to previous menu):");
        Transaction e = null;
        if (amount==0) {
            stay = false;
        }
        if (stay) {
            map.put("amount", amount);
            e = transactionHelperOne(map);
        }
        return e;
    }

    /***
     * Pay Bill
     * Apply pay bill feature under selected account
     */

    /***
     * Select the account used to pay
     * @param uam UserAccountManager of the user
     */
    private void payBillMenu(UserAccManager uam) {
        Map<String, Object> map = new HashMap<>();
        map.put("Type", "PayBill");
        map.put("fromAccount", getTypeAccountMenu(uam, "Payable"));
        if (map.get("fromAccount") != null) {
            payBillToMenu(map);
        }
    }

    /***
     * Enter the identity that the bill is paid to
     * @param map recording information of transaction
     */
    private void payBillToMenu(Map<String, Object> map){
        boolean stay = true;
        String input = typer.promptUser("Please enter whom the bill is paid to (Enter 'back' if you want to go back " +
                "to previous menu):");
        if (input == "back") {
            stay = false;
        }
        if (stay) {
            map.put("to", input);
            getDoubleAmountMenu(map);
        }
    }

    /***
     * Withdrawal
     * Apply withdraw under selected account
     */

    /***
     * Select the account used to pay
     * @param uam UserAccountManager of the user
     */
    private void withdrawalMenu(UserAccManager uam) {
        Map<String, Object> map = new HashMap<>();
        map.put("Type", "Withdrawal");
        map.put("fromAccount", getTypeAccountMenu(uam, "Withdrawable"));
        if (map.get("fromAccount") != null) {
            Transaction e = getIntAmountMenu(map);
        }
    }

    /***
     * Deposit
     * deposing the entering amount into the primary chequing account
     * @param uam UsrAccountManager of this user
     */
    private void depositMenu(UserAccManager uam) {
        Map<String, Object> map = new HashMap<>();
        map.put("Type", "Deposit");
        map.put("toAccount", uam.getPrimaryChq());
        if (map.get("toAccount") != null) {
            getIntAmountMenu(map);
        }
    }


    /***
     * Regular transaction
     */

    /***
     * Apply the regular transactions between the selected accounts
     * @param uam UserAccountManager of this user
     */
    private void regularTransactionMenu(UserAccManager uam) {
        Map<String, Object> map = new HashMap<>();
        map.put("Type", "Regular");
        map.put("fromAccount", getTypeAccountMenu(uam, "TransferOutable"));
        if (map.get("fromAccount") != null) {
            regularTransToHelperMenu(uam, "TransferInable", map);
            if (map.get("toAccount") != null) {
                getDoubleAmountMenu(map);
            }
        }
    }

    private void regularTransToHelperMenu(UserAccManager uam, String type, Map<String, Object> map) {
        boolean stay = true;
        String[] list = {"Transfer to my account", "Transfer to other user's account", "I don't want to make " +
                "this transaction"};
        printFixedMenu(list);
        String input = typer.ensureOption(1,3);
        if (input.equals("3")) {
            stay = false;
        }
        if (stay) {
            Account acc = null;
            switch (input) {
                case "1":
                    acc = getTypeAccountMenu(uam, type);
                    break;
                case "2":
                    acc = regularTransToOtherMenu(uam, type);
                    break;
            }
            map.put("toAccount", acc);
        }
    }

    private Account regularTransToOtherMenu(UserAccManager uam, String type){
        boolean stay = true;
        String input = typer.ensureTypeAccount(typeCheckerPicker(type));
        Account acc = null;
        if (input.equals("back")) {
            stay = false;
        }
        if (stay) {
            try {
                acc = InfoManager.getInfoManager().getAccount(input);
            } catch (NoSuchAccountException e){
                System.out.println(e.getMessage());
            }
        }
        return acc;
    }

    /** Users send their request for the creation of new account.*/
    private void userReqAccSubMenu(User user){
        ManagerMenus m = new ManagerMenus();
        m.printCreateAccountMenu();
        String chosen = typer.ensureOption(1, 6);
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
}
