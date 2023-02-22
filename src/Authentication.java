import java.util.Scanner;

public class Authentication {
    JDBC jdbc = new JDBC();
    MenuAdmin menuAdmin = new MenuAdmin();
    MenuUser menuUser = new MenuUser();
    public void register() {
        Scanner scanner = new Scanner(System.in);
        boolean isRegistered = false;
        while (!isRegistered) {
            try {
                System.out.print("Enter your username: ");
                String username = scanner.nextLine();
                System.out.print("Enter your password: ");
                String password = scanner.nextLine();
                isRegistered = jdbc.registerUser(username, password, false);
                if (isRegistered) {
                    System.out.println("Registration successful!");
                } else {
                    System.out.println("This username is already taken. Please try again.");
                }
            } catch (AuthenticationException e) {
                System.out.println("Registration failed:" + e.getMessage());
            }
        }
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);
        boolean isAuthenticated = false;
        while (!isAuthenticated) {
            System.out.println("Choose an option:");
            System.out.println("1. Login");
            System.out.println("2. Login as admin");
            int option = scanner.nextInt();
            System.out.print("Enter your username: ");
            String username = scanner.next();
            isAuthenticated = jdbc.loginUser(username, scanner, option);
            if (isAuthenticated) {
                if (option == 1) {
                    System.out.println("Login successful!");
                    menuUser.menu(scanner);
                } else {
                    System.out.println("Admin login successful!");
                    menuAdmin.menu(scanner);
                }
            } else {
                System.out.println("Invalid credentials. Please try again.");
            }
        }
    }
}