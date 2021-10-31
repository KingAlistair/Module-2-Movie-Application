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
                System.out.println("Welcome " + user.getUserName() + "!\n");
                mainMenu(user);
            } else {
                System.out.println("Password is incorrect! Try again!");
                login();
            }
        }
    }

    public void register() {
        //Get accountList from file
        ArrayList<Account> accountList = FileIO.accountListDeserialization();


        //Refistration
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

        //Create Account add it to accountlist
        Account account = new Account(username, password);
        accountList.add(account);

        //accountList saved into File
        FileIO.accountListSerialization(accountList);

        System.out.println("Registration is complete. Please log in!\n");
        login();
    }

    public void mainMenu(Account user) {
        System.out.println("Main menu:");
        System.out.println();
        System.out.println("1. All movies");
        System.out.println("2. Favorite movies (" + user.favoriteMovies.size() + ")");
        System.out.println("3. History (" + user.seenMovies.size() + ")");
        System.out.println("4 Search movie");
        System.out.println("5. Quit");

        String input = stringScanner();

        switch (input) {
            case "1":
                displayMovieList(FileIO.movieListDeserialization());
                mainMenu(user);
                break;

            case "2":
                favoriteMovies(user);
                mainMenu(user);
                break;

            case "3":
                history(user);
                mainMenu(user);
                break;

            case "4":
                search();
                mainMenu(user);
                break;

            case "5":
                System.exit(0);
                break;

            default:
                mainMenu(user);
                break;
        }

    }

    public void favoriteMovies(Account user) {
        ArrayList<Movie> allMovies = FileIO.movieListDeserialization();
        ArrayList<Movie> favoriteList = user.favoriteMovies;
        System.out.println("Favorite list:");
        displayMovieList(favoriteList);

        System.out.println("1. Add movie");
        System.out.println("2. Remove movie");
        System.out.println("3. Rate movie");
        System.out.println(" Press anything else to Return to main menu");
        String input = stringScanner();

        switch (input) {

            //Add movie
            case "1":
                System.out.println("All movies:");
                displayMovieList(allMovies);
                System.out.println("Choose a Movie ID to add it your Favorite list!");
                input = stringScanner();

                //Check if already in favoriteList
                for (Movie movie : favoriteList
                ) {
                    if (input.equals(movie.getId())) {
                        System.out.println(movie.getTitle() + " is already in your Favorite list!");
                        history(user);
                        break;
                    }
                }
                //Check if its in allMoviesList
                for (Movie movie2 : allMovies
                ) {
                    if (input.equalsIgnoreCase(movie2.getId())) {
                        System.out.println("Do you want to add " + movie2.getTitle() + " to your Favorite list? (y/n)");
                        input = stringScanner();
                        if (input.equalsIgnoreCase("y")) {
                            System.out.println();
                            System.out.println(movie2.getTitle() + " was added to your Favorite list!");
                            System.out.println();
                            favoriteList.add(movie2);
                            Account.saveUserIntoFile(user);
                            history(user);
                            break;
                        }
                    }
                }
                System.out.println("Movie was not found in our database!");
                history(user);
                break;
//Remove movie
            case "2":
                displayMovieList(favoriteList);
                System.out.println("Type in the \"ID\" of the movie you want to remove!");
                input = stringScanner();

                //Check if movie is in list
                for (Movie movie : favoriteList) {
                    if (input.equals(movie.getId())) {
                        System.out.println("Do you want to remove " + movie.getTitle() + " from your Favorite list? (y/n)");
                        input = stringScanner();

                        if (input.equalsIgnoreCase("y")) {
                            favoriteList.remove(movie);
                            user.setFavoriteMovies(favoriteList);
                            Account.saveUserIntoFile(user);
                            favoriteMovies(user);
                            break;
                        }
                    }
                }

                System.out.println("Movie was not found on your Favorite list!");
                favoriteMovies(user);
                break;
            default:
                mainMenu(user);
                break;
        }
    }

    public void history(Account user) {
        ArrayList<Movie> allMovies = FileIO.movieListDeserialization();
        ArrayList<Movie> seenMovieList = user.seenMovies;
        System.out.println("History:");
        displayMovieList(seenMovieList);

        System.out.println("1. Add movie");
        System.out.println("2. Remove movie");
        System.out.println("3. Rate movie");
        System.out.println(" Press anything else to Return to main menu");
        String input = stringScanner();

        switch (input) {

            //Add movie
            case "1":
                System.out.println("All movies:");
                displayMovieList(allMovies);
                System.out.println("Choose a Movie ID to add it your History list!");
                input = stringScanner();

                //Check if already in seenMovieList
                for (Movie movie : seenMovieList
                ) {
                    if (input.equals(movie.getId())) {
                        System.out.println(movie.getTitle() + " is already in your History list!");
                        history(user);
                        break;
                    }
                }
                //Check if its in allMoviesList
                for (Movie movie2 : allMovies
                ) {
                    if (input.equalsIgnoreCase(movie2.getId())) {
                        System.out.println("Do you want to add " + movie2.getTitle() + " to your History list? (y/n)");
                        input = stringScanner();
                        if (input.equalsIgnoreCase("y")) {
                            System.out.println();
                            System.out.println(movie2.getTitle() + " was added to your History list!");
                            System.out.println();
                            seenMovieList.add(movie2);
                            Account.saveUserIntoFile(user);
                            history(user);
                            break;
                        }
                    }
                }
                System.out.println("Movie was not found in our database!");
                history(user);
                break;
//Remove movie
            case "2":
                displayMovieList(seenMovieList);
                System.out.println("Type in the \"ID\" of the movie you want to remove!");
                input = stringScanner();

                //Check if movie is in list
                for (Movie movie : seenMovieList) {
                    if (input.equals(movie.getId())) {
                        System.out.println("Do you want to remove " + movie.getTitle() + " from your History list? (y/n)");
                        input = stringScanner();

                        if (input.equalsIgnoreCase("y")) {
                            seenMovieList.remove(movie);
                            user.setFavoriteMovies(seenMovieList);
                            Account.saveUserIntoFile(user);
                            favoriteMovies(user);
                            break;
                        }
                    }
                }

                System.out.println("Movie was not found on your History list!");
                favoriteMovies(user);
                break;
            default:
                mainMenu(user);
                break;
        }
    }

    public void search() {
        System.out.println("Search: ");
        ArrayList<Movie> allMovies = FileIO.movieListDeserialization();
        String input = stringScanner();
        for (Movie movie : allMovies
        ) {
            if (input.equalsIgnoreCase(movie.title) || input.equalsIgnoreCase(movie.genre)) {
                movie.displayMovie();
            }
            for (Actor actor : movie.getActorList()
            ) {
                if (input.equalsIgnoreCase(actor.name)) {
                    System.out.println(actor.name + " : " + movie.title);
                }
            }
        }
    }

    public void displayMovieList(ArrayList<Movie> movieList) {
        for (Movie movie : movieList
        ) {
            movie.displayMovie();
        }
    }


    public String stringScanner() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public int intScanner() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public static void createDataFiles() {
        //Create accountList.ser file with Accounts
        FileIO.accountListSerialization(Database.getAccountList());
        FileIO.movieListSerialization(Database.getMovieList());
    }
}
