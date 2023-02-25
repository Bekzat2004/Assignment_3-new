
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Authentication authentication = new Authentication();
        System.out.println("Welcome to the Marketplace!");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> authentication.register();
            case 2 -> authentication.login();
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }
}
