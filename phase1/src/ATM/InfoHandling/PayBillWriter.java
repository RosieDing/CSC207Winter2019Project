package ATM.InfoHandling;

public class PayBillWriter implements WriteTXT{
    private String path = "./outgoing.txt";

    public void write(String content){

    }

    public void setPath(String newPath) {
        this.path = newPath;
    }
}
