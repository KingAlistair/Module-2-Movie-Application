import java.util.ArrayList;

public class AccountDataBase {
    private ArrayList<Account> accountList = new ArrayList<>();

    public void accountList() {
        ArrayList<Account> accountsList = new ArrayList<>();
        Account test = new Account("Test","Test");
        accountsList.add(test);
    }

    public ArrayList<Account> getAccountList() {
        return accountList;
    }
}
