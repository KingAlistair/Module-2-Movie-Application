import java.util.ArrayList;

public class Account {
   private String userName;
   private String password;
   ArrayList<Movie> favoriteMovies = new ArrayList<>();
   ArrayList<Movie> seenMovies = new ArrayList<>();


   public Account(String userName, String password) {
      this.userName = userName;
      this.password = password;
   }
}
