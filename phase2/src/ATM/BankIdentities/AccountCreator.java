package ATM.BankIdentities;

import ATM.Accounts.*;
import ATM.Accounts.Plans.GICPlans.GICPlan;
import ATM.Accounts.Plans.Plan;
import ATM.InfoHandling.InfoStorer;


import java.util.ArrayList;
import java.util.Map;
/** A class that create a new  Account*/
public class AccountCreator {
    private Map<String, User> userMap;
    private  Map<String, BankStaff> staffMap;
    private int totalAccNum;
    private Map<String, Account> accountMap;
    private AccountOwnable user;
    private String type;
    public AccountCreator(AccountOwnable user, InfoStorer infoStorer, String type){
        userMap = infoStorer.getUserMap();
        staffMap = infoStorer.getStaffMap();
        totalAccNum = infoStorer.getAccountMap().size();
        accountMap = infoStorer.getAccountMap();
        this.user = user;
        this.type = type;
    }


    /** Create New Chequing Account for the User
     *
     * @throws AlreadyPrimaryException
     *  */
    public void createNewChequingAccount() {
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
     *  */
    public void createNewSavingAccount(Plan plan){
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
     * @param limit the limit of the credit account
     *  */
    public void createNewCreditAccount(double limit){
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
     * @param limit the limit of the line of credit account
     *  */
    public void createNewLineOfCredit(double limit){
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
     *  */
    public void createNewGICAccount(GICPlan plan, double principle){
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
