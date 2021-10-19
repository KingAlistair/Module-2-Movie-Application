import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO {


    public static void writeAccount (ArrayList<Account> accountList) throws IOException {
        File file = new File("accountList.txt");

        FileWriter writer = new FileWriter("output.txt");
        for(Account account: accountList) {
            writer.write(account + System.lineSeparator());
        }
        writer.close();
    }
}
