package ATM.BankIdentities;

import ATM.Accounts.*;
import ATM.Accounts.ISaverPlans.ISaverPlan;
import ATM.Accounts.ISaverPlans.MonthlyInterest;

import java.util.ArrayList;
import java.util.Map;

public class AccountCreator {

    /** Create New Chequing Account for the User
     *
     * @param userID the userID of the user
     * @throws AlreadyPrimaryException
     *  */
    public void createNewChequingAccount(int totalAccNum, String userID, Map<String, ArrayList<Account>> accountListMap) {
        UserAccManager m = new UserAccManager(userID, accountListMap);
        ArrayList userList = new ArrayList();
        userList.add(userID);
        ChequingAccount acc = new ChequingAccount(userList, totalAccNum);
        if (m.getPrimaryChq() == null){
            try {
                m.setPrimaryChq(acc);
            } catch(AlreadyPrimaryException e ){
                System.out.println("This is already the Primary Account");
            }
        }
        try {
            m.addAccount(acc);
        } catch (UserNotOwnAccountException e) {
            System.out.println(e);
        }
    }

    /** Create New Saving Account for the User
     *
     * @param userID the userID of the user
     *  */
    public void createNewSavingAccount(String userID, Map<String, ArrayList<Account>> accountListMap, int totalAccNum, ISaverPlan plan){
        UserAccManager m = new UserAccManager(userID, accountListMap);
        ArrayList userList = new ArrayList();
        userList.add(userID);
        SavingAccount acc = new SavingAccount(userList, plan, totalAccNum);
        try {
            m.addAccount(acc);
        } catch (UserNotOwnAccountException e) {
            System.out.println(e);
        }
    }


    /** Create New Credit Account for the User
     *
     * @param userID the userID of the user
     * @param limit the limit of the credit account
     *  */
    public void createNewCreditAccount(String userID, double limit,  int totalAccNum, Map<String, ArrayList<Account>> accountListMap){
        UserAccManager m = new UserAccManager(userID, accountListMap);
        ArrayList userList = new ArrayList();
        userList.add(userID);
        CreditAccount acc = new CreditAccount(userList, limit, totalAccNum);
        try {
            m.addAccount(acc);
        } catch (UserNotOwnAccountException e) {
            System.out.println(e);
        }
    }

    /** Create New Line of Credit Account for the User
     *
     * @param userID the userID of the user
     * @param limit the limit of the line of credit account
     *  */
    public void createNewLineOfCredit(String userID, double limit, int totalAccNum, Map<String, ArrayList<Account>> accountListMap){
        UserAccManager m = new UserAccManager(userID, accountListMap);
        ArrayList userList = new ArrayList();
        userList.add(userID);
        LineOfCredit acc = new LineOfCredit(userList, limit, totalAccNum);
        try {
            m.addAccount(acc);
        } catch (UserNotOwnAccountException e) {
            System.out.println(e);
        }
    }
}
