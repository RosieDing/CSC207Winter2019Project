package ATM;

import ATM.BankIdentities.BankIdentity;
import ATM.BankIdentities.BankManager;
import ATM.BankIdentities.PasswordManager;
import ATM.BankIdentities.User;
import ATM.InfoHandling.InfoManager;
import ATM.Machine.CashMachine;
import ATM.Machine.Money;

import javax.sound.sampled.Line;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BankSystem {
    private static boolean SystemOn;
    private static InfoManager infoManager = InfoManager.getInfoManager();

    public static void main(String[] args) {
       if(infoManager.getBankManagerNum() == 0){
           BankManager defaultManager = new BankManager("1234");
       }
        while(true){
            BankSystem bs = new BankSystem();
            bs.run();
        }
    }

    public void run() {
    }

    public void identityLog(){
        boolean isFound = false;
        while (!isFound) {
            String id = promptUser("Please enter your ID: ");
            if (infoManager.getInfoStorer().getBankManagerMap().containsKey(id)) {
                managerLog(id);
                isFound = true;
            } else if (infoManager.getInfoStorer().getUserMap().containsKey(id)){
                userLog(id);
                isFound = true;
            }

        }
    }

    public void managerLog(String id){
        BankManager bankManager = infoManager.getBankManager(id);
        PasswordManager passwordManager = bankManager.getPassManager();
        System.out.println("Welcome, our bank manager~");
        String pw = promptUser("Please enter your password: ");
        passwordManager.login(pw);
        while (!passwordManager.isLogin()){
            pw = promptUser("Please re-enter your password: ");
            passwordManager.login(pw);
        }
        managerMainMenu(bankManager);
    }
    public void userLog(String id){
        User user = infoManager.getUser(id);
        PasswordManager passwordManager = user.getPassManager();
        System.out.println("Welcome, our customer~");
        String pw = promptUser("Please enter your password: ");
        passwordManager.login(pw);
        while (!passwordManager.isLogin()){
            pw = promptUser("Please re-enter your password: ");
            passwordManager.login(pw);
        }
        userMainMenu(user);
    }

    public void printManagerMenu(){

    }

    public void managerMainMenu(BankManager bankManager){
        while (bankManager.getPassManager().isLogin()){
            printManagerMenu();
            String chosen = ensureOption(1, 5);
            switch (chosen){
                case "1":
                    bankManager.createUser();
                    break;
                case "2":
                    String accNum = promptUser("Please enter an account number: ");
                    bankManager.undoMostRecentTrans(accNum);
                    break;
                case "3":
                    managerSubMenu(bankManager);
                    break;
                case "4":
                    int numFive = ensureInt("Please enter the amount of five dolloars you want to restock");
                    int numTen = ensureInt("Please enter the amount of ten dolloars you want to restock");
                    int numTwenty = ensureInt("Please enter the amount of twenty dolloars you want to restock");
                    int numFifty = ensureInt("Please enter the amount of fifty dolloars you want to restock");
                    Money m = new Money(numFive, numTen, numTwenty, numFifty);
                    bankManager.restock(infoManager.getCashMachine(), m);
                    break;
                case "5":
                    bankManager.getPassManager().logout();
            }
        }
    }

    private void printManagerSubMenu(){


    }
    private void managerSubMenu(BankManager bankManager){
        printManagerSubMenu();
        String chosen = ensureOption(1, 4);
        String userID = promptUser("Please enter a user ID: ");
            switch(chosen){
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
                    bankManager.createNewLineOfCreadit(userID, limitN);
                    break;
        }
    }

    public void userMainMenu(User user){

    }

    private Double ensureDouble(String prompt){
        boolean isEnsured = false;
        String input = "";
        while (!isEnsured) {
            input = promptUser(prompt);
            try {
                Double d = Double.valueOf(input);
                isEnsured = true;
            } catch (NumberFormatException nfe){
                System.out.println("Please remember to enter a number.");
            }
        }
        return Double.valueOf(input);
    }

    private int ensureInt(String prompt){
        boolean isEnsured = false;
        String input = "";
        while (!isEnsured) {
            input = promptUser(prompt);
            try {
                Integer i = Integer.valueOf(input);
                isEnsured = true;
            } catch (NumberFormatException nfe){
                System.out.println("Please remember to enter an integer.");
            }
        }
        return Integer.valueOf(input);
    }

    private String ensureOption(int min, int max){
        boolean isEnsured = false;
        String chose = "";
        while (!isEnsured){
            chose = promptUser("Please enter an integer ranging from " + min + " to " + max + ":");
            try {
                Integer i = Integer.valueOf(chose);
                if (min <= i && i <= max){
                    isEnsured = true;
                    }
            } catch (NumberFormatException nfe){
                System.out.println("Please remember to enter an integer.");
            }
        }
        return chose;
    }

    private String promptUser(String prompt){
        BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(prompt);
        try{
            String input = kbd.readLine();
            kbd.close();
            return input;
        } catch(IOException ex) {
            return ex.toString();
        }
    }
}
