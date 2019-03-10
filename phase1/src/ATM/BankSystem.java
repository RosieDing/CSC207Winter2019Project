package ATM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BankSystem {
    private static boolean SystemOn;

    public static void main(String[] args) {
        String a = promptUser("name?");
        System.out.println(a);
    }

    private static String promptUser(String prompt){
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
