import java.util.ArrayList;

public class Account implements java.io.Serializable {
    private String userName;
    private String password;
    private  ArrayList<Movie> favoriteMovies;
    private ArrayList<Movie> seenMovies;
    private ArrayList<Movie> votedMovies;

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.favoriteMovies = new ArrayList<>();
        this.seenMovies = new ArrayList<>();
        this.votedMovies = new ArrayList<>();
    }

    public Account(String userName, String password, ArrayList<Movie> favoriteMovies, ArrayList<Movie> seenMovies, ArrayList<Movie> votedMovies) {
        this.userName = userName;
        this.password = password;
        this.favoriteMovies = favoriteMovies;
        this.seenMovies = seenMovies;
        this.votedMovies = votedMovies;
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

    public ArrayList<Movie> getVotedMovies() {
        return votedMovies;
    }

    public void setFavoriteMovies(ArrayList<Movie> favoriteMovies) {
        this.favoriteMovies = favoriteMovies;
    }

    public void setSeenMovies(ArrayList<Movie> seenMovies) {
        this.seenMovies = seenMovies;
    }

    //Replaces original Account in list from updated Account, saves it into file
    public static void saveUserIntoFile(Account user) {
        ArrayList<Account> accountlist = FileIO.accountListDeserialization();

        //Organise thy lists...
        Menu.organiseUserLists(user);

        for (int i = 0; i < accountlist.size(); i++) {
            if (user.getUserName().equals(accountlist.get(i).getUserName()))
            {
                accountlist.set(i,user);
                FileIO.accountListSerialization(accountlist);;
            }
        }
        System.out.println("-Account was saved-\n");
    }
}
