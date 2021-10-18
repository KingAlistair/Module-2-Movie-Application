import java.util.ArrayList;

public class Movie {
    String title;
    String genre;
    int releaseYear;
    ArrayList<Actor> actorList;

    public Movie(String title, String genre, int releaseYear, ArrayList<Actor> actorList) {
        this.title = title;
        this.genre = genre;
        this.actorList = actorList;
        this.releaseYear = releaseYear;
    }

    public void displayMovie() {
        System.out.println("==================================");
        System.out.println("Title: " + title);
        System.out.println("Genre: " + genre);
        System.out.println("Release date:"  + releaseYear);
        System.out.println();
        System.out.println("Actors:");
        for (Actor actor: actorList
             ) {
            System.out.println(actor);
        }
        System.out.println("==================================");
    }
}
