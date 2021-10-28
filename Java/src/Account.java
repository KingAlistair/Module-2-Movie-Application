import java.util.ArrayList;

public class Account implements java.io.Serializable {
    private String userName;
    private String password;
    ArrayList<Movie> favoriteMovies = new ArrayList<>();
    ArrayList<Movie> seenMovies = new ArrayList<>();


    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Movie> getFavoriteMovies() {
        return favoriteMovies;
    }

    public ArrayList<Movie> getSeenMovies() {
        return seenMovies;
    }

    @Override
    public String toString() {
        return userName+";"+password+";";
    }
}
