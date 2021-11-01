import java.util.ArrayList;

public class Account implements java.io.Serializable {
    private String userName;
    private String password;
    ArrayList<Movie> favoriteMovies;
    ArrayList<Movie> seenMovies;
    ArrayList<Movie> votedMovies;

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.favoriteMovies = new ArrayList<>();
        this.seenMovies = new ArrayList<>();
        this.votedMovies = new ArrayList<>();
    }

    public Account(String userName, String password, ArrayList<Movie> favoriteMovies, ArrayList<Movie> seenMovies) {
        this.userName = userName;
        this.password = password;
        this.favoriteMovies = favoriteMovies;
        this.seenMovies = seenMovies;
        this.votedMovies = new ArrayList<>();
    }

    public static void saveUserIntoFile(Account user) {
        ArrayList<Account> accountlist = FileIO.accountListDeserialization();

        for (int i = 0; i < accountlist.size(); i++) {
            if (user.getUserName().equals(accountlist.get(i).getUserName()))
            {
                accountlist.set(i,user);

                FileIO.accountListSerialization(accountlist);
            }
        }
        System.out.println("Your account was saved!\n");
    }

    public ArrayList<Movie> getVotedMovies() {
        return votedMovies;
    }

    public void setVotedMovies(ArrayList<Movie> votedMovies) {
        this.votedMovies = votedMovies;
    }

    public void setFavoriteMovies(ArrayList<Movie> favoriteMovies) {
        this.favoriteMovies = favoriteMovies;
    }

    public void setSeenMovies(ArrayList<Movie> seenMovies) {
        this.seenMovies = seenMovies;
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
