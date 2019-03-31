package ATM.BankIdentities;

import ATM.Accounts.*;
import ATM.Accounts.Plans.GICPlans.GICPlan;
import ATM.Accounts.Plans.Plan;


import java.util.ArrayList;
import java.util.Map;
/** A class that create a new  Account*/
public class AccountCreator {

    /** Create New Chequing Account for the User
     *
     * @param user the User
     * @throws AlreadyPrimaryException
     *  */
    public void createNewChequingAccount(int totalAccNum, AccountOwnable user,String type, Map<String, Account> accountMap) {
        String userID = user.getId();
        UserAccManager m = new UserAccManager(userID, accountMap);
        ArrayList<String> userList = new ArrayList<>();
        userList.add(userID);
        ChequingAccount acc = new ChequingAccount(userList, totalAccNum, type);
        m.addGlobalMap(acc.getAccountNum(), acc);
        if (user.getPrimaryChq() == null){
            try {
                user.setPrimaryChq(acc);
            } catch(AlreadyPrimaryException e ){
                System.out.println("This is already the Primary Account");
            }
        }
        try {
            user.addAccount(acc);
        } catch (UserNotOwnAccountException e) {
            System.out.println(e);
        }
    }

    /** Create New Saving Account for the User
     *
     * @param user the user
     *  */
    public void createNewSavingAccount(int totalAccNum, AccountOwnable user, String type, Map<String, Account> accountMap, Plan plan){
        String userID = user.getId();
        UserAccManager m = new UserAccManager(userID, accountMap);
        ArrayList<String> userList = new ArrayList<>();
        userList.add(userID);
        SavingAccount acc = new SavingAccount(userList, plan, totalAccNum,type);
        m.addGlobalMap(acc.getAccountNum(), acc);
        try {
            user.addAccount(acc);
        } catch (UserNotOwnAccountException e) {
            System.out.println(e);
        }
    }


    /** Create New Credit Account for the User
     *
     * @param user the user
     * @param limit the limit of the credit account
     *  */
    public void createNewCreditAccount(int totalAccNum, AccountOwnable user, double limit,String type, Map<String, Account> accountMap){
        String userID = user.getId();
        UserAccManager m = new UserAccManager(userID, accountMap);
        ArrayList userList = new ArrayList();
        userList.add(userID);
        CreditAccount acc = new CreditAccount(userList, limit, totalAccNum,type);
        m.addGlobalMap(acc.getAccountNum(), acc);
        try {
            user.addAccount(acc);
        } catch (UserNotOwnAccountException e) {
            System.out.println(e);
        }
    }

    /** Create New Line of Credit Account for the User
     *
     * @param user the user
     * @param limit the limit of the line of credit account
     *  */
    public void createNewLineOfCredit(int totalAccNum, AccountOwnable user, double limit,String type, Map<String, Account> accountMap){
        String userID = user.getId();
        UserAccManager m = new UserAccManager(userID, accountMap);
        ArrayList userList = new ArrayList();
        userList.add(userID);
        LineOfCredit acc = new LineOfCredit(userList, limit, totalAccNum,type);
        try {
            user.addAccount(acc);
        } catch (UserNotOwnAccountException e) {
            System.out.println(e);
        }
    }

    /** Create New GIC Account for the User
     *
     * @param user the user
     *  */
    public void createNewGICAccount(int totalAccNum, AccountOwnable user,String type,
                                    Map<String, Account> accountMap, GICPlan plan, double principle){
        String userID = user.getId();
        UserAccManager m = new UserAccManager(userID, accountMap);
        ArrayList<String> userList = new ArrayList<>();
        userList.add(userID);
        GICAccount acc = new GICAccount(userList, totalAccNum, plan, principle,type);
        m.addGlobalMap(acc.getAccountNum(), acc);
        try {
            user.addAccount(acc);
        } catch (UserNotOwnAccountException e) {
            System.out.println(e);
        }
    }
}
