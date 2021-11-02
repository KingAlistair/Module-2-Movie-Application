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
        System.out.println();
    }

    @Override
    public String toString() {
        return "Title: " + title + "\n" +
                "Genre: " + genre + "\n" +
                "Release date: " + releaseYear + "\n" +
                "Actors: " + "\n";
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

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public ArrayList<Actor> getActorList() {
        return actorList;
    }

    public void setActorList(ArrayList<Actor> actorList) {
        this.actorList = actorList;
    }

    public ArrayList<Double> getRating() {
        return rating;
    }

    public void setRating(ArrayList<Double> rating) {
        this.rating = rating;
    }
}