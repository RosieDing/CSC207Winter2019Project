package ATM;

import java.util.Observable;

public abstract class BankIdentity extends Observable {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        setChanged();
        notifyObservers();
    }
}
