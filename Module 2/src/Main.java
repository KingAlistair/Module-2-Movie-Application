import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.createDatabases();

        Menu menu = new Menu();
        menu.welcome();
    }

    public void createDatabases() throws IOException {
        //Create movie and actor arraylists, load default data
        MovieAndActorDatabase movieAndActorDatabase = new MovieAndActorDatabase();
        movieAndActorDatabase.createMovieDatabase();

        AccountDataBase accountDataBase = new AccountDataBase();
        accountDataBase.accountList();

        FileIO.writeAccount(accountDataBase.getAccountList());
    }
}