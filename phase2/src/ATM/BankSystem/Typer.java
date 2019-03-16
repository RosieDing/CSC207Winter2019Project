package ATM.BankSystem;

import ATM.InfoHandling.InfoManager;

import java.util.Scanner;

public class Typer {
    public String ensureDouble(String prompt) {
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
        return input;
    }

    public int ensureInt(String prompt) {
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

    public String ensureID() {
        boolean isEnsured = false;
        String input = "";
        while (!isEnsured) {
            input = promptUser("Please enter a user ID: ");
            if (InfoManager.getInfoManager().getInfoStorer().getUserMap().containsKey(input)) {
                isEnsured = true;
            } else {
                System.out.println("You did not enter a existing user id!");
            }
        }
        return input;
    }

    public String ensurePassword(int length) {
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

    public String ensureOption(int min, int max) {
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

    public String promptUser(String prompt) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println(prompt);
        String input = keyboard.nextLine();
        return input;
    }
}
