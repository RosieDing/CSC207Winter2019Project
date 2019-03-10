package ATM;

import ATM.BankIdentities.BankIdentity;
import ATM.BankIdentities.BankManager;
import ATM.BankIdentities.PasswordManager;
import ATM.BankIdentities.User;
import ATM.InfoHandling.InfoManager;
import ATM.Machine.CashMachine;

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
           System.out.println("You are the first bank manager.");
           System.out.println("ID: " + defaultManager.getId() + ", Password: 1234");
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
            passwordManager.login(pw);;
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
            passwordManager.login(pw);;
        }
    }

    public void managerMainMenu(){


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
