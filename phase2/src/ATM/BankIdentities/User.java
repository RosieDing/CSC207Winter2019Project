package ATM.BankIdentities;

import java.util.Map;

/** a class that represent a bank user */
public class User extends BankIdentity {

    /** The id of the user */
    private final String id;

    /**
     * Create a new user
     * @param totalUserNum The total number of the users created*/
    public User(int totalUserNum) {
        this.id = "020" + totalUserNum + 1;
    }

    /** Return the Id of the user
     * @return id
     */
    public String getId(){
        return id;
    }

    /** Send a request to tell Manager to create account
     * @param type The account type needed to be added
     * @param requestMap The global request map from User ID to the request type
     */
    public void sendRequest(String type, Map<String, String> requestMap){
        requestMap.put(getId(), type);
    }
}
