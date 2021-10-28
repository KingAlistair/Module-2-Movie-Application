import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public void welcome() {
        System.out.println("Welcome to the Movie app!");

        System.out.println("1: Login");
        System.out.println("2: Create a new account");
        System.out.println("3: Reset databases");

        String input = stringScanner();

        switch (input) {

            case "1":
                login();
                break;

            case "2":
                register();
                break;

            case "3":
                createDataFiles();
                welcome();
                break;

            default:
                System.out.println("Press 1 to login, or 2 to register");
                welcome();
                break;
        }
    }

    public void login() {
        //Create Arraylist, load from accountList.ser
        ArrayList<Account> accountList = FileIO.accountListDeserialization();
        System.out.println("We currently have " + accountList.size() + " users.");

        //Enter username/password, check it with Account objects from Arraylist
        System.out.println("Please enter your username:");
        String input = stringScanner();
        Account user = null;

        for (Account account : accountList) {
            if (input.equals(account.getUserName())) {
                user = account;
            }
        }
        if (user == null) {
            System.out.println("User was not found try again!");
            login();
        } else {
            System.out.println("Enter password");
            input = stringScanner();
            if (input.equals(user.getPassword())) {
                System.out.println("You have logged in successfully!\n");
                mainMenu(user);
            } else {
                System.out.println("Password is incorrect! Try again!");
                login();
            }
        }
    }

    public void register() {
        ArrayList<Account> accountList = FileIO.accountListDeserialization();

        String username = null;
        boolean loop = true;
        boolean isUsed;

        while (loop) {
            isUsed = false;
            System.out.println("Choose an username!");
            username = stringScanner();

            for (Account account : accountList
            ) {
                if (username.equals(account.getUserName())) {
                    isUsed = true;
                }
            }

            if (!isUsed) {
                System.out.println("Username is available!");
                loop = false;
            } else {
                System.out.println("Username already in use\n!");
            }
        }

        System.out.println("Choose a password!");
        String password = stringScanner();

        Account account = new Account(username, password);
        accountList.add(account);

        FileIO.accountListSerialization(accountList);

        System.out.println("Registration is complete. Please log in!\n");
        login();
    }

    public void mainMenu(Account user) {
        System.out.println("Welcome, " + user.getUserName() + "!\n");
        System.out.println("Main menu:");
        System.out.println("1. All movies");
        System.out.println("2. Favorite movies");
        System.out.println("3. History");
        System.out.println("4. Quit");

        String input = stringScanner();

        if (input.equals("1")) {
            displayMovies(user);
        }

        if (input.equals("2")) {
        }

        if (input.equals("3")) {
        }

        if (input.equals("4")) {
            System.exit(1);
        } else {
            mainMenu(user);
        }
    }

    public void displayMovies(Account user) {
        ArrayList<Movie> movieList = FileIO.movieListDeserialization();

        System.out.println("There are currently " + movieList.size() + " movies available!\n");

        for (Movie movie : movieList) {
            movie.displayMovie();
        }
        mainMenu(user);
    }

    public String stringScanner() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        return input;
    }

    public static void createDataFiles() {
        //Create accountList.ser file with Accounts
        FileIO.accountListSerialization(Database.getAccountList());
        FileIO.movieListSerialization(Database.getMovieList());
    }
}
