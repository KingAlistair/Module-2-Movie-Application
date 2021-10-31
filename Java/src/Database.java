import java.util.ArrayList;

public class Database {

    public static ArrayList<Account> getAccountList () {
        ArrayList<Movie> allMovieList = getMovieList();
        ArrayList<Movie> favoriteList = new ArrayList<>();
        ArrayList<Movie> seenList = new ArrayList<>();
        ArrayList<Account> accountList = new ArrayList<>();

        Movie movie1 = allMovieList.get(0);
        Movie movie2 = allMovieList.get(1);
        Movie movie3 = allMovieList.get(3);
        Movie movie4 = allMovieList.get(4);

        seenList.add(movie1);
        seenList.add(movie2);
        seenList.add(movie3);
        seenList.add(movie4);
        favoriteList.add(movie1);
        favoriteList.add(movie3);

        Account account1 = new Account("Test","test");
        Account account2 = new Account("Tim","enchanter",favoriteList,seenList);

        accountList.add(account1);
        accountList.add(account2);

        return accountList;
    }

    public static ArrayList<Movie> getMovieList() {

        ArrayList<Movie> movieList = new ArrayList<>();
        ArrayList<Actor> actorList = new ArrayList<>();
        ArrayList<Double> rating = new ArrayList<>();

        //Titanic
        Actor diCaprio = new Actor("Leonardo Dicaprio", "Jack Dawson");
        Actor kateWinslet = new Actor("Kate Winslet", "Rose Dewitt Bukater");
        Actor billyZane = new Actor("Billy Zane", "Cal Hockley");

        actorList.add(diCaprio);
        actorList.add(kateWinslet);
        actorList.add(billyZane);

        rating.add(7.0);
        rating.add(8.5);
        rating.add(7.8);
        rating.add(7.4);
        rating.add(7.3);

        Movie titanic = new Movie("1","Titanic", "Romantic", "1997",actorList,rating);

        //Mad Max

        //restart ArrayList
        actorList = new ArrayList<>();
        rating = new ArrayList<>();

        Actor tomHardy = new Actor("Tom Hardy", "Max Rockatansky");
        Actor charlizeTheron = new Actor("Charlize Theron", "Imperator Furiosa");
        Actor nicholasHoult = new Actor("Nicholas Hoult", "Nux");

        actorList.add(tomHardy);
        actorList.add(charlizeTheron);
        actorList.add(nicholasHoult);

        rating.add(6.4);
        rating.add(8.5);
        rating.add(7.9);
        rating.add(7.4);
        rating.add(7.1);

        Movie madmax = new Movie("2","Mad Max", "Action", "2015",actorList,rating);

        //Dune

        //restart ArrayList
        actorList = new ArrayList<>();
        rating = new ArrayList<>();

        Actor timotheeChalamet = new Actor("Timothée Chalamet", "Paul Atreides");
        Actor rebeccaFerguson = new Actor("Rebecca Ferguson", "Lady Jessica Atreides");

        actorList.add(timotheeChalamet);
        actorList.add(rebeccaFerguson);

        rating.add(5.4);
        rating.add(7.5);
        rating.add(9.1);
        rating.add(7.4);
        rating.add(5.1);

        Movie dune = new Movie("3","Dune", "Action", "2021",actorList,rating);

    //The Shawshank Redemption

    //restart ArrayList
    actorList = new ArrayList<>();
    rating = new ArrayList<>();

    Actor timRobbins = new Actor("Tim Robbins", "Andy Dufresne");
    Actor morganFreeman = new Actor("Morgan Freeman", "Red");

        actorList.add(timRobbins);
        actorList.add(morganFreeman);

        rating.add(7.4);
        rating.add(9.5);
        rating.add(8.9);
        rating.add(8.4);
        rating.add(9.1);

    Movie shawshank = new Movie("4","Shawshank Redemption", "Drama", "1994",actorList,rating);


        //Fight Club

        //restart ArrayList
        actorList = new ArrayList<>();
        rating = new ArrayList<>();

        Actor edwardNorton = new Actor("Edward Norton", "The Narrator");
        Actor bradPitt = new Actor("Brad Pitt", "Tyler Durden");

        actorList.add(edwardNorton);
        actorList.add(bradPitt);

        rating.add(9.9);
        rating.add(7.9);
        rating.add(6.9);
        rating.add(7.4);
        rating.add(8.8);
        rating.add(8.6);

        Movie fightClub = new Movie("5","Fight Club","Drama","1999",actorList,rating);

        movieList.add(titanic);
        movieList.add(madmax);
        movieList.add(dune);
        movieList.add(shawshank);
        movieList.add(fightClub);

        return movieList;
    }
}

