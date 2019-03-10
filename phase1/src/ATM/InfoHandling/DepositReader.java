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
    private Map<Depositable, Double> disposedAccounts = new HashMap();
        public void txtReader(String fileName) throws IOException {
            Path path = Paths.get(fileName);
            try (BufferedReader fileInput = Files.newBufferedReader(path)) {
                String line = fileInput.readLine();
                int length = line.length();
                while (line != null) {
                    String[] c = line.split(" ");
                        for (int b = 0; b <= length; b = +2) {
                            Depositable d = InfoManager.getAccount(Integer.valueOf(b));
                            Double k = Double.valueOf(c[b + 1]);
                            disposedAccounts.put(d, k);
                        }
                    line = fileInput.readLine();
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }

    }
}
