import java.util.Scanner;

public class Menu {

    public void welcome() {
        System.out.println("Welcome to the Movie app!");

        boolean loop = true;
        while (loop) {

            System.out.println("1: Login");
            System.out.println("2: Register");

            String input = stringScanner();

            if (input.equals("1")) {
                loop = false;
                login();
            }
            if (input.equals("2")) {
                loop = false;
                register();
            } else {
                System.out.println("If you have and account press 1. If you want to register press 2.");
            }
        }
    }

    public void login() {
        System.out.println("Please enter your username");
        String input = stringScanner();


    }

    public void register() {

    }

    public void mainMenu() {
        System.out.println("Movies seen");
    }

    public String stringScanner() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        return input;
    }
}
