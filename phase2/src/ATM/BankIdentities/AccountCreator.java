package ATM.BankIdentities;

import ATM.Accounts.*;
import ATM.Accounts.ISaverPlans.MonthlyInterest;
import ATM.InfoHandling.InfoManager;

public class AccountCreator {
    /** Create New Chequing Account for the User
     *
     * @param userID the userID of the user
     * @throws AlreadyPrimaryException
     *  */
    public void createNewChequingAccount(String userID) {
        User u = InfoManager.getInfoManager().getUser(userID);
        UserAccManager m = u.getAccManager();
        ChequingAccount acc = new ChequingAccount(userID);
        if (m.getPrimaryChq() == null){
            try {
                m.setPrimaryChq(acc);
            } catch(AlreadyPrimaryException e ){
                System.out.println();
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
    public void createNewSavingAccount(String userID){
        User u = InfoManager.getInfoManager().getUser(userID);
        UserAccManager m = u.getAccManager();
        MonthlyInterest interest = new MonthlyInterest(0.01);
        SavingAccount acc = new SavingAccount(userID, interest);
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
    public void createNewCreditAccount(String userID, double limit){
        User u = InfoManager.getInfoManager().getUser(userID);
        UserAccManager m = u.getAccManager();
        CreditAccount acc = new CreditAccount(userID, limit);
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
    public void createNewLineOfCredit(String userID, double limit){
        User u = InfoManager.getInfoManager().getUser(userID);
        UserAccManager m =u.getAccManager();
        LineOfCredit acc = new LineOfCredit(userID, limit);
        try {
            m.addAccount(acc);
        } catch (UserNotOwnAccountException e) {
            System.out.println(e);
        }
    }
}
