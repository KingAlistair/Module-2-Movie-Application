import java.util.ArrayList;

public class Database {

    public static ArrayList<Account> getAccountList () {
        ArrayList<Account> accountList = new ArrayList<>();

        Account account = new Account("Test","test");
        Account account1 = new Account("1","1");
        Account account2 = new Account("Tim","enchanter");

        accountList.add(account);
        accountList.add(account1);
        accountList.add(account2);

        return accountList;
    }

    public static ArrayList<Movie> getMovieList() {

        ArrayList<Movie> movieList = new ArrayList<>();
        ArrayList<Actor> actorList = new ArrayList<>();

        //Titanic
        Actor diCaprio = new Actor("Leonardo Dicaprio", "Jack Dawson");
        Actor kateWinslet = new Actor("Kate Winslet", "Rose Dewitt Bukater");
        Actor billyZane = new Actor("Billy Zane", "Cal Hockley");

        actorList.add(diCaprio);
        actorList.add(kateWinslet);
        actorList.add(billyZane);

        Movie titanic = new Movie("Titanic", "Romantic", "1997",actorList);

        //Mad Max

        //restart ArrayList
        actorList = new ArrayList<>();

        Actor tomHardy = new Actor("Tom Hardy", "Max Rockatansky");
        Actor charlizeTheron = new Actor("Charlize Theron", "Imperator Furiosa");
        Actor nicholasHoult = new Actor("Nicholas Hoult", "Nux");

        actorList.add(tomHardy);
        actorList.add(charlizeTheron);
        actorList.add(nicholasHoult);

        Movie madmax = new Movie("Mad Max", "Action", "2015",actorList);

        movieList.add(titanic);
        movieList.add(madmax);

        return movieList;
    }
}

