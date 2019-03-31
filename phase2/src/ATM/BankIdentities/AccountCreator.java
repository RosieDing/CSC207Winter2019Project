package ATM.BankIdentities;

import ATM.Accounts.*;
import ATM.Accounts.Plans.GICPlans.GICPlan;
import ATM.Accounts.Plans.Plan;
import ATM.InfoHandling.InfoStorer;


import java.util.ArrayList;
import java.util.Map;
/** A class that create a new  Account*/
public class AccountCreator {




    /** Create New Chequing Account for the User
     *
     * @param user the User
     * @throws AlreadyPrimaryException
     *  */
    public void createNewChequingAccount(AccountOwnable user, String type, InfoStorer infoStorer) {
        Map<String, User> userMap = infoStorer.getUserMap();
        Map<String, BankStaff> staffMap = infoStorer.getStaffMap();
        int totalAccNum = infoStorer.getAccountMap().size();
        Map<String, Account> accountMap = infoStorer.getAccountMap();
        String userID = user.getId();
        UserAccManager m = new UserAccManager(userID, userMap, staffMap);
        ArrayList<String> userList = new ArrayList<>();
        userList.add(userID);
        ChequingAccount acc = new ChequingAccount(userList, totalAccNum, type);
        m.addGlobalMap(acc.getAccountNum(), acc, accountMap);
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
    public void createNewSavingAccount(AccountOwnable user, String type, Plan plan, InfoStorer infoStorer){
        Map<String, User> userMap = infoStorer.getUserMap();
        Map<String, BankStaff> staffMap = infoStorer.getStaffMap();
        int totalAccNum = infoStorer.getAccountMap().size();
        Map<String, Account> accountMap = infoStorer.getAccountMap();
        String userID = user.getId();
        UserAccManager m = new UserAccManager(userID, userMap, staffMap);
        ArrayList<String> userList = new ArrayList<>();
        userList.add(userID);
        SavingAccount acc = new SavingAccount(userList, plan, totalAccNum,type);
        m.addGlobalMap(acc.getAccountNum(), acc, accountMap);
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
    public void createNewCreditAccount(AccountOwnable user, double limit, String type,InfoStorer infoStorer){
        Map<String, User> userMap = infoStorer.getUserMap();
        Map<String, BankStaff> staffMap = infoStorer.getStaffMap();
        int totalAccNum = infoStorer.getAccountMap().size();
        Map<String, Account> accountMap = infoStorer.getAccountMap();
        String userID = user.getId();
        UserAccManager m = new UserAccManager(userID, userMap, staffMap);
        ArrayList userList = new ArrayList();
        userList.add(userID);
        CreditAccount acc = new CreditAccount(userList, limit, totalAccNum,type);
        m.addGlobalMap(acc.getAccountNum(), acc, accountMap);
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
    public void createNewLineOfCredit(AccountOwnable user, double limit, String type, InfoStorer infoStorer){
        Map<String, User> userMap = infoStorer.getUserMap();
        Map<String, BankStaff> staffMap = infoStorer.getStaffMap();
        int totalAccNum = infoStorer.getAccountMap().size();
        Map<String, Account> accountMap = infoStorer.getAccountMap();
        String userID = user.getId();
        UserAccManager m = new UserAccManager(userID, userMap, staffMap);
        ArrayList userList = new ArrayList();
        userList.add(userID);
        LineOfCredit acc = new LineOfCredit(userList, limit, totalAccNum,type);
        m.addGlobalMap(acc.getAccountNum(), acc, accountMap);
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
    public void createNewGICAccount(AccountOwnable user, String type, GICPlan plan, double principle, InfoStorer infoStorer){
        Map<String, User> userMap = infoStorer.getUserMap();
        Map<String, BankStaff> staffMap = infoStorer.getStaffMap();
        int totalAccNum = infoStorer.getAccountMap().size();
        Map<String, Account> accountMap = infoStorer.getAccountMap();
        String userID = user.getId();
        UserAccManager m = new UserAccManager(userID, userMap, staffMap);
        ArrayList<String> userList = new ArrayList<>();
        userList.add(userID);
        GICAccount acc = new GICAccount(userList, totalAccNum, plan, principle,type);
        m.addGlobalMap(acc.getAccountNum(), acc, accountMap);
        try {
            user.addAccount(acc);
        } catch (UserNotOwnAccountException e) {
            System.out.println(e);
        }
    }
}
