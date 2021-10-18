public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome\n");
        
        MovieAndActorDatabase movieAndActorDatabase = new MovieAndActorDatabase();

        movieAndActorDatabase.createMovieDatabase();
        movieAndActorDatabase.displayAllMOvie();
    }
}
