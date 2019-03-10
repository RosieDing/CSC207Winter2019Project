package ATM.InfoHandling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/** */
public class DepositReader {

    public void txtReader(String fileName){
        Path path = Paths.get(fileName);
        try (BufferedReader fileInput = Files.newBufferedReader(path)) {
            String line = fileInput.readLine();
            while (line != null) { // Reading from a file will produce null at the end.
                System.out.println(line);
                line = fileInput.readLine();
            }
        } catch (IOException ex){
            System.out.println(ex);
        }
    }
}
