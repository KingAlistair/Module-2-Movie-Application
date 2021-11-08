import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Movie implements java.io.Serializable {
    private String id;
    private String title;
    private String genre;
    private String releaseYear;
    private ArrayList<Actor> actorList;
    private ArrayList<Double> rating;

    public Movie(String id, String title, String genre, String releaseYear, ArrayList<Actor> actorList, ArrayList<Double> rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.actorList = actorList;
        this.rating = rating;
    }

    public void shortDisplayMovie() {
        System.out.println("Id: " + id + "| Title: " + title);
    }

    //Format to display Movies
    public void displayMovie() {
        System.out.println("====================================");
        System.out.println("Id: " + id);
        System.out.println("Title: " + title);
        System.out.println("Genre: " + genre);
        System.out.println("Release date: " + releaseYear);
        System.out.println("Rating: " + calculateRating());
        System.out.println();
        System.out.println("Actors:");
        for (Actor actor : actorList
        ) {
            System.out.println(actor);
        }
        System.out.println("====================================");
    }

    public Double calculateRating() {
        //Average
        double avrgRate = 0.0;

        for (Double rate : rating
        ) {
            avrgRate += rate;
        }
        avrgRate /= rating.size();


        //Rounding double
        BigDecimal bd = BigDecimal.valueOf(avrgRate);
        bd = bd.setScale(1, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public ArrayList<Actor> getActorList() {
        return actorList;
    }

    public ArrayList<Double> getRating() {
        return rating;
    }
}