package ATM.InfoHandling;

import java.io.*;

public class BillWriter implements WriteTXT, Serializable {
    private String path = "./outgoing.txt";

    public void write(String content){
        File file = new File(path);
        try{
            if(file.exists()==false){
                file.createNewFile();
            }
            PrintWriter out = new PrintWriter(new FileWriter(file, true));

            out.append(content + "\n");
            out.close();
        }catch(IOException e){
            System.out.println("Pay Bill is not recorded.");
        }
    }

    public void setPath(String newPath) {
        this.path = newPath;
    }
}
