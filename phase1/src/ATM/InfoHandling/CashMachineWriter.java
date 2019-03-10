package ATM.InfoHandling;

public class CashMachineWriter implements WriteTXT {
    private String path = "./alert.txt";

    public void write(String content){

    }

    public void setPath(String newPath) {
        this.path = newPath;
    }
}
