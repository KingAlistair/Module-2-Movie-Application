import java.io.*;
import java.util.ArrayList;

public class FileIO {


    public static void accountListSerialization(ArrayList<Account> accountList) {

        try {
            FileOutputStream fos = new FileOutputStream("accountList.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(accountList);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static ArrayList<Account> accountListDeserialization() {
        ArrayList<Account> accountList = null;
        try {
            FileInputStream fis = new FileInputStream("accountList.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);

            accountList = (ArrayList) ois.readObject();

            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }
        return accountList;
    }

    public static void movieListSerialization(ArrayList<Movie> movieList) {
        try {
            FileOutputStream writeData = new FileOutputStream("movieList.ser");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(movieList);
            writeStream.flush();
            writeStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<Movie> movieListDeserialization() {

        ArrayList<Movie> movieList = null;

        try {
            FileInputStream readData = new FileInputStream("movieList.ser");
            ObjectInputStream readStream = new ObjectInputStream(readData);

            movieList = (ArrayList<Movie>) readStream.readObject();
            readStream.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return movieList;
    }
}