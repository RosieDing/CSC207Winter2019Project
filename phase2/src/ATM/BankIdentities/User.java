package ATM.BankIdentities;

import java.util.Map;

/** Class User */
public class User extends BankIdentity {
    private final String id;

    /**
     * Create a new user
     * @param totalUserNum The total number of the users created*/
    public User(int totalUserNum) {
        this.id = "020" + totalUserNum + 1;
    }

    /** return the Id of the user */
    public String getId(){
        return id;
    }

    /** the user send the request to the Manager to add the account
     * @param type the account type needed be added
     * @param requestMap
     * */
    public void sendRequest(String type, Map<String, String> requestMap){
        requestMap.put(getId(), type);
    }
}
