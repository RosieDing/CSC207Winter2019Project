package ATM.BankIdentities;

import ATM.InfoHandling.InfoManager;

import java.io.Serializable;
import java.util.Observable;

/** BankIdentity abstract class */
public abstract class BankIdentity implements Serializable {
    public abstract String getId();
    public InfoManager infoManager = new InfoManager();
}
