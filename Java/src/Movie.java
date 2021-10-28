import java.util.ArrayList;

public class Movie implements java.io.Serializable{
    String title;
    String genre;
    String releaseYear;
    ArrayList<Actor> actorList;

    public Movie(String title, String genre, String releaseYear, ArrayList<Actor> actorList) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.actorList = actorList;
    }

    public void setActorList(ArrayList<Actor> actorList) {
        this.actorList = actorList;
    }

    public void displayMovie() {
        System.out.println("==================================");
        System.out.println("Title: " + title);
        System.out.println("Genre: " + genre);
        System.out.println("Release date: "  + releaseYear);
        System.out.println();
        System.out.println("Actors:");
        for (Actor actor: actorList
             ) {
            System.out.println(actor);
        }
        System.out.println("==================================\n");
    }

    @Override
    public String toString() {
        return "Title: " + title + "\n" +
                "Genre: " + genre + "\n" +
                "Release date: " + releaseYear + "\n" +
                "Actors: " + "\n";
    }
}
