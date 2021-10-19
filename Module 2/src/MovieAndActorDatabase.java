import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class MovieAndActorDatabase {
    ArrayList<Actor> allActorList = new ArrayList<>();
    ArrayList<Movie> allMovieList = new ArrayList<>();


    public void createMovieDatabase() {

    //Titanic:

        //Create empty ArrayList for Actors
        ArrayList<Actor> actorList = new ArrayList<Actor>();

        //Create actor objects
        Actor diCaprio = new Actor("Leonardo Dicaprio", "Jack Dawson");
        Actor kateWinslet = new Actor("Kate Winslet", "Rose Dewitt Bukater");
        Actor billyZane = new Actor("Billy Zane", "Cal Hockley");

        //Add actor objects to ArrayList
        actorList.add(diCaprio);
        actorList.add(kateWinslet);
        actorList.add(billyZane);

        //Create movie object
        Movie titanic = new Movie("Titanic", "Romantic", 1997, actorList);

        //Add Actors and Movie to allActorList and allMovieList
        allActorList.add(diCaprio);
        allActorList.add(kateWinslet);
        allActorList.add(billyZane);
        allMovieList.add(titanic);

    //Mad Max

        //"Restart ArrayList"
        actorList = new ArrayList<>();

        //Create actor objects
        Actor tomHardy = new Actor("Tom Hardy", "Max Rockatansky");
        Actor charlizeTheron = new Actor("Charlize Theron", "Imperator Furiosa");
        Actor nicholasHoult = new Actor("Nicholas Hoult", "Nux");

        //Add actor objects to ArrayList
        actorList.add(tomHardy);
        actorList.add(charlizeTheron);
        actorList.add(nicholasHoult);

        //Create movie object
        Movie madmax = new Movie("Mad Max", "Action", 2015, actorList);

        //Add Actors and Movie to allActorList and allMovieList
        allActorList.add(diCaprio);
        allActorList.add(kateWinslet);
        allActorList.add(billyZane);
        allMovieList.add(madmax);

    //Next Movie
    }

    public ArrayList<Actor> getAllActorList() {
        return allActorList;
    }

    public ArrayList<Movie> getAllMovieList() {
        return allMovieList;
    }

    public void displayAllMovie() {
        for (Movie movie: allMovieList
             ) {
            movie.displayMovie();
        }
    }

}



