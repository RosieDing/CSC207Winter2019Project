import javax.print.attribute.standard.DateTimeAtCreation;
import java.io.Serializable;

public abstract class DebtAccount extends Account {
    private int limit;
    private int ownerID;


    public DebtAccount(int ownerID, int limit){
        super(ownerID);
        this.limit = limit;
    }

    abstract void setLimit;
    abstract double getLimit;

}