import java.util.ArrayList;

public class Movie implements java.io.Serializable {
    String id;
    String title;
    String genre;
    String releaseYear;
    ArrayList<Actor> actorList;
    ArrayList<Double> rating;

    public Movie(String id,String title, String genre, String releaseYear, ArrayList<Actor> actorList, ArrayList<Double> rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.actorList = actorList;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public String getGenre() {
        return genre;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Actor> getActorList() {
        return actorList;
    }

    public ArrayList<Double> getRating() {
        return rating;
    }

    public void setRating(ArrayList<Double> rating) {
        this.rating = rating;
    }

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
        double avrgRate = 0.0;


        for (Double rate : rating
        ) {
            avrgRate += rate;
        }
        avrgRate /= rating.size();


        avrgRate = Math.round(avrgRate * 1000.0) / 1000.0;


        return avrgRate;
    }
}
