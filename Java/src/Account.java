import java.util.ArrayList;

public class Account implements java.io.Serializable {
    private String userName;
    private String password;
    ArrayList<Movie> favoriteMovies;
    ArrayList<Movie> seenMovies;

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.favoriteMovies = new ArrayList<>();
        this.seenMovies = new ArrayList<>();
    }

    public Account(String userName, String password, ArrayList<Movie> favoriteMovies, ArrayList<Movie> seenMovies) {
        this.userName = userName;
        this.password = password;
        this.favoriteMovies = favoriteMovies;
        this.seenMovies = seenMovies;
    }

    public static void saveUserIntoFile(Account user) {
        ArrayList<Account> accountlist = FileIO.accountListDeserialization();

        for (int i = 0; i < accountlist.size(); i++) {
            if (user.getUserName().equals(accountlist.get(i).getUserName()))
            {
                accountlist.set(i,user);
                System.out.println("Your account was saved!");
                FileIO.accountListSerialization(accountlist);
            }
        }
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
