package ATM.InfoHandling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import ATM.Accounts.*;
import ATM.InfoHandling.*;


/** */
public class DepositReader {
    private Map<Depositable, Double> depositedAccounts = new HashMap();
        public void txtReader(String fileName) throws IOException {
            Path path = Paths.get(fileName);
            try (BufferedReader fileInput = Files.newBufferedReader(path)) {
                String line = fileInput.readLine();
                while (line != null) {
                    String[] c = line.split(" ");
                    Depositable d = InfoManager.getAccount(Integer.valueOf(c[0]));
                    Double k = Double.valueOf(c[1]);
                    depositedAccounts.put(d, k);
                    line = fileInput.readLine();
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }

    }
}
