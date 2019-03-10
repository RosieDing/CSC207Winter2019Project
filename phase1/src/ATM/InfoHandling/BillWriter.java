package ATM.InfoHandling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BillWriter implements WriteTXT{
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
