import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Menu {

    //Welcome menu
    public void welcome() {
        System.out.println();
        System.out.println("Welcome to the Movie app!");
        System.out.println("--------------------------");
        System.out.println("1: Login                  ");
        System.out.println("2: Create a new account\n");
        System.out.println("3: Reset databases\n");

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
        //Create accountList, load with data from accountList.ser
        ArrayList<Account> accountList = FileIO.accountListDeserialization();
        System.out.println("We currently have " + accountList.size() + " users.");

        //Enter username/password, check it with Account objects from Arraylist
        System.out.println("Enter your username:");
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
            System.out.println("Enter your password:");
            input = stringScanner();
            if (input.equals(user.getPassword())) {
                System.out.println();
                System.out.println("You have logged in successfully!\n");
                System.out.println("Welcome " + user.getUserName() + "!\n");
                updateUserLists(user);
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

        //Registration
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
                System.out.println("Username is available!\n");
                loop = false;
            } else {
                System.out.println("Username already in use!\n");
            }
        }

        System.out.println("Choose a password!");
        String password = stringScanner();

        //Create Account, add it to accountlist
        Account account = new Account(username, password);
        accountList.add(account);

        //accountList saved into File
        FileIO.accountListSerialization(accountList);
        System.out.println("Registration is complete. Please log in!\n");
        login();
    }

    public void mainMenu(Account user) {
        ArrayList<Movie> allMovies = FileIO.movieListDeserialization();

        System.out.println("Main menu:");
        System.out.println();
        System.out.println("1. Play a movie (" + allMovies.size() + ")");
        System.out.println("2. Favorite movies (" + user.getFavoriteMovies().size() + ")");
        System.out.println("3. History (" + user.getSeenMovies().size() + ")");
        System.out.println("4. Search (Movie/Actor/Genre/Release date/ID)");
        System.out.println("5. Rate movie");
        System.out.println("6. Log out");

        String input = stringScanner();

        switch (input) {
            case "1":
                watchMovie(user);
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
                rateMovie(user);
                mainMenu(user);
                break;

            case "6":
                System.out.println("You have logged out successfully!");
                welcome();
                break;

            default:
                mainMenu(user);
                break;
        }
    }

    public void watchMovie(Account user) {
        ArrayList<Movie> allMovies = FileIO.movieListDeserialization();
        ArrayList<Movie> seenMovies = user.getSeenMovies();
        ArrayList<Movie> favoriteMovies = user.getFavoriteMovies();
        System.out.println("Start seenmovies:" + seenMovies.size() + "favorite " + favoriteMovies.size());

        //Display all movies
        System.out.println();
        System.out.println("Movie List: ");
        displayShortMovieList(allMovies);

        System.out.println("\nChoose a Movie 'ID' to watch, press enter to return to Main menu.");
        String input = stringScanner();
        Boolean isInDatabase = false;

        //Enter returns to menu
        if (input.equals("")) {
            mainMenu(user);
        }

        //Check if chosen ID is in Database
        for (Movie movie : allMovies
        ) {
            if (input.equalsIgnoreCase(movie.getId())) {
                Movie chosenMovie = movie;
                isInDatabase = true;

                //Playing movie
                System.out.println("Playing " + chosenMovie.getTitle() + " now...");
                chosenMovie.displayMovie();
                System.out.println("The end!\n");
                boolean isInSeenMovies = false;

                //Check if already in seenMovies
                for (Movie movie2 : seenMovies
                ) {
                    if (input.equals(movie2.getId())) {
                        System.out.println(movie2.getTitle() + " is already in your History list!");
                        isInSeenMovies = true;
                    }
                }
                if (!isInSeenMovies) {
                    seenMovies.add(chosenMovie);
                    user.setSeenMovies(seenMovies);
                    Account.saveUserIntoFile(user);
                    System.out.println(chosenMovie.getTitle() + " was added to your history!");
                }

                //Check if already in favoriteList
                boolean isInFavorites = false;
                for (Movie movie3 : favoriteMovies
                ) {
                    if (input.equals(movie3.getId())) {
                        System.out.println(movie.getTitle() + " is already in your Favorite list!\n");
                        isInFavorites = true;
                    }
                }
                if (!isInFavorites) {
                    System.out.println(chosenMovie.getTitle() + " is not on your Favorite list. Do you want to add it? (y/n)");

                    if (userInputYesOrNo()) {
                        favoriteMovies.add(chosenMovie);
                        user.setFavoriteMovies(favoriteMovies);
                        Account.saveUserIntoFile(user);
                        System.out.println(chosenMovie.getTitle() + " was added to your Favorite list!");
                    }
                }
            }
        }
        if (!isInDatabase) {
            System.out.println("Movie was not found. Try again!");
            watchMovie(user);
        }

        System.out.println("Returning to Main menu...\n");
        mainMenu(user);
    }

    public void favoriteMovies(Account user) {

        //Create ArrayLists, load data from file
        ArrayList<Movie> allMovies = FileIO.movieListDeserialization();
        ArrayList<Movie> favoriteList = user.getFavoriteMovies();
        System.out.println("Favorite list:");
        displayShortMovieList(favoriteList);

        System.out.println("-------------------------------------");
        System.out.println("1. Add movie");
        System.out.println("2. Remove movie");
        System.out.println(" Press 'ENTER' to Return to main menu");
        System.out.println("-------------------------------------");
        String input = stringScanner();

        switch (input) {

            //Add movie
            case "1":
                System.out.println("All movies:");
                displayShortMovieList(allMovies);
                System.out.println("---------------------------------------------------");
                System.out.println("Type in a Movie ID to add it to your Favorite list!");
                input = stringScanner();

                //Check if already in favoriteList
                for (Movie movie : favoriteList
                ) {
                    if (input.equals(movie.getId())) {
                        System.out.println(movie.getTitle() + " is already in your Favorite list!\n");
                        favoriteMovies(user);
                        break;
                    }
                }
                //Check if its in allMoviesList
                for (Movie movie2 : allMovies
                ) {
                    if (input.equalsIgnoreCase(movie2.getId())) {
                        System.out.println("Do you want to add " + movie2.getTitle() + " to your Favorite list? (y/n)");

                        if (userInputYesOrNo()) {
                            System.out.println();
                            System.out.println(movie2.getTitle() + " was added to your Favorite list!");
                            System.out.println();
                            favoriteList.add(movie2);
                            Account.saveUserIntoFile(user);
                            favoriteMovies(user);
                            break;
                        } else {
                            System.out.println("Return to Favorite list...\n");
                            favoriteMovies(user);
                            break;
                        }
                    }
                }
                System.out.println("Movie was not found in our database!");
                favoriteMovies(user);
                break;
//Remove movie
            case "2":
                displayShortMovieList(favoriteList);
                System.out.println("---------------------------------------------------");
                System.out.println("Type in the \"ID\" of the movie you want to remove!");
                input = stringScanner();

                //Check if movie is in list
                for (Movie movie : favoriteList) {
                    if (input.equals(movie.getId())) {
                        System.out.println("Do you want to remove " + movie.getTitle() + " from your Favorite list? (y/n)");

                        if (userInputYesOrNo()) {
                            favoriteList.remove(movie);
                            user.setFavoriteMovies(favoriteList);
                            Account.saveUserIntoFile(user);
                            favoriteMovies(user);
                            break;
                        } else {
                            System.out.println("Return to Favorite list...\n");
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
        ArrayList<Movie> seenMovieList = user.getSeenMovies();
        System.out.println("History:");
        displayShortMovieList(seenMovieList);

        System.out.println("-------------------------------------");
        System.out.println("1. Add movie");
        System.out.println("2. Remove movie");
        System.out.println(" Press 'ENTER' to Return to main menu");
        System.out.println("-------------------------------------");
        String input = stringScanner();

        switch (input) {

            //Add movie
            case "1":
                System.out.println("All movies:");
                for (Movie movie : allMovies
                ) {
                    movie.shortDisplayMovie();
                }

                System.out.println("----------------------------------------------");
                System.out.println("Choose a Movie ID to add it your History list!");
                input = stringScanner();

                //Check if already in seenMovieList
                for (Movie movie : seenMovieList
                ) {
                    if (input.equals(movie.getId())) {
                        System.out.println(movie.getTitle() + " is already in your History list!\n");
                        history(user);
                        break;
                    }
                }
                //Check if its in allMoviesList
                for (Movie movie2 : allMovies
                ) {
                    if (input.equalsIgnoreCase(movie2.getId())) {
                        System.out.println("Do you want to add " + movie2.getTitle() + " to your History list? (y/n)");
                        if (userInputYesOrNo()) {
                            System.out.println();
                            System.out.println(movie2.getTitle() + " was added to your History list!");
                            System.out.println();
                            seenMovieList.add(movie2);
                            Account.saveUserIntoFile(user);
                            history(user);
                            break;
                        } else {
                            System.out.println("Return to History list...\n");
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
                displayShortMovieList(seenMovieList);
                System.out.println("---------------------------------------------------");
                System.out.println("Type in the \"ID\" of the movie you want to remove!");
                input = stringScanner();

                //Check if movie is in list
                for (Movie movie : seenMovieList) {
                    if (input.equals(movie.getId())) {
                        System.out.println("Do you want to remove " + movie.getTitle() + " from your History list? (y/n)");

                        if (userInputYesOrNo()) {
                            seenMovieList.remove(movie);
                            user.setSeenMovies(seenMovieList);
                            Account.saveUserIntoFile(user);
                            history(user);
                            break;
                        } else {
                            System.out.println("Return to History list...\n");
                            history(user);
                            break;
                        }

                    }
                }
                System.out.println("Movie was not found on your History list!");
                history(user);
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
            if (input.equalsIgnoreCase(movie.getTitle()) || input.equalsIgnoreCase(movie.getGenre()) || input.equals(movie.getReleaseYear()) || input.equals(movie.getId())) {
                movie.displayMovie();
            }
            for (Actor actor : movie.getActorList()
            ) {
                if (input.equalsIgnoreCase(actor.getName())) {
                    System.out.println(actor.getName() + " : " + movie.getTitle());
                }
            }
        }
        System.out.println();
    }

    public void rateMovie(Account user) {
        //Create Arraylists, load them with data
        ArrayList<Movie> allMovies = FileIO.movieListDeserialization();
        ArrayList<Movie> votedMovies = user.getVotedMovies();
        displayShortMovieList(allMovies);

        System.out.println("----------------------------------------------");
        System.out.println("Write in the ID of the movie you want to rate!");
        System.out.println("You can only rate a movie once!");
        String input = stringScanner();

        //Check if movie is in database, return it
        Movie chosenMovie = checkMovieInArray(input, allMovies);
        if (chosenMovie != null) {

            //Check if movie is in votedList
            Movie isVotedMovie = checkMovieInArray(input, votedMovies);

            //If not, user can vote
            if (isVotedMovie == null) {
                boolean loop = true;
                Double doubleVote = null;

                while (loop) {
                    System.out.println("Rate " + chosenMovie.getTitle() + " (1-10)");
                    String vote = stringScanner();
                    doubleVote = Double.parseDouble(vote);

                    if (doubleVote >= 1.0 && doubleVote <= 10.0) {
                        ArrayList<Double> rating = chosenMovie.getRating();
                        rating.add(doubleVote);

                        for (Movie movie : allMovies
                        ) {
                            if (chosenMovie.getId().equals(movie.getId())) {
                                chosenMovie = movie;

                                //Save user and movielist into File
                                votedMovies.add(chosenMovie);
                                FileIO.movieListSerialization(allMovies);
                                Account.saveUserIntoFile(user);
                                System.out.println("Rating was successful!");
                                loop = false;
                            }
                        }

                    } else {
                        System.out.println("Invalid rating!");

                    }
                }
            } else {
                System.out.println("You have already voted for this movie!\n");
            }
        } else {
            System.out.println("Movie was not found in database!");
            System.out.println("Return to Main menu...\n");
            mainMenu(user);
        }
    }

    public void displayShortMovieList(ArrayList<Movie> movieList) {
        for (Movie movie : movieList
        ) {
            movie.shortDisplayMovie();
        }
    }

    public String stringScanner() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }


    public boolean userInputYesOrNo() {
        String input = stringScanner();

        if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
            return true;
        } else {
            return false;
        }
    }

    public static void createDataFiles() {
        //Create accountList.ser file with Accounts
        FileIO.accountListSerialization(Database.getAccountList());
        FileIO.movieListSerialization(Database.getMovieList());
    }

    //Check if Movie is in arraylist, returns movie if yes or null if no
    public static Movie checkMovieInArray(String input, ArrayList<Movie> checkList) {
        for (Movie movie : checkList
        ) {
            if (input.equals(movie.getId())) {
                return movie;
            }
        }
        return null;
    }

    //Update user favorite and history list after rating
    public static void updateUserLists(Account user) {
        ArrayList<Movie> favoriteList = user.getFavoriteMovies();
        ArrayList<Movie> seenList = user.getSeenMovies();
        ArrayList<Movie> allMovies = FileIO.movieListDeserialization();

        //Update favorite list
        for (Movie movie : allMovies
        ) {
            for (int i = 0; i < favoriteList.size(); i++) {
                if (favoriteList.get(i).getId().equals(movie.getId())) {
                    favoriteList.set(i, movie);
                }
            }
        }

        //Update seen list
        for (Movie movie : allMovies
        ) {
            for (int i = 0; i < seenList.size(); i++) {
                if (seenList.get(i).getId().equals(movie.getId())) {
                    seenList.set(i, movie);
                }
            }
        }

        //Set lists
        user.setFavoriteMovies(favoriteList);
        user.setSeenMovies(seenList);
        Account.saveUserIntoFile(user);
    }

    public static Account organiseUserLists(Account user) {
        ArrayList<Movie> allMovie = FileIO.movieListDeserialization();
        ArrayList<Movie> seenMovies = user.getSeenMovies();
        ArrayList<Integer> seenId = new ArrayList<>();
        ArrayList<Movie> organisedSeenMovies = new ArrayList<>();
        ArrayList<Movie> favoriteMovies = user.getFavoriteMovies();
        ArrayList<Integer> favoriteId = new ArrayList<>();
        ArrayList<Movie> organisedFavoriteMovies = new ArrayList<>();

        //Organise History List
        for (Movie movie : seenMovies
        ) {
            seenId.add(Integer.parseInt(movie.getId()));
        }
        Collections.sort(seenId);

        for (Integer id : seenId
        ) {
            for (Movie movie : allMovie
            ) {
                if (id == Integer.parseInt(movie.getId())) {
                    organisedSeenMovies.add(movie);
                }
            }
        }
        user.setSeenMovies(organisedSeenMovies);

        //Organised Favorite
        for (Movie movie : favoriteMovies
        ) {
            favoriteId.add(Integer.parseInt(movie.getId()));
        }
        Collections.sort(favoriteId);

        for (Integer id : favoriteId
        ) {
            for (Movie movie : allMovie
            ) {
                if (id == Integer.parseInt(movie.getId())) {
                    organisedFavoriteMovies.add(movie);
                }
            }
        }
        user.setFavoriteMovies(organisedFavoriteMovies);
        return user;
    }
}