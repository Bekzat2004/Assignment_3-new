import java.util.Scanner;

public class MenuAdmin {
    public static void menu(Scanner scanner )  {
        boolean isOpen = true;
        while (isOpen) {
            System.out.println("Welcome to Administrator page!");
            System.out.println("1. Find a car");
            System.out.println("2. Publish new car");
            System.out.println("3. Update specifications of car");
            System.out.println("4. Browse all automobiles");
            System.out.println("5. Browse all users");
            System.out.println("6. Add user");
            System.out.println("7. Exit");
            switch (scanner.nextInt()) {
                case 1 -> Functionality.findCar();
                case 2 -> Functionality.publishCar();
                case 3 -> Functionality.updateCar();
                case 4 -> Functionality.showCar();
                case 5 -> Functionality.getUserInfo();
                case 6 -> Functionality.addUser();
                case 7 -> isOpen = false;
                default -> System.out.println("Pick a number between 1 and 7");
            }
        }
    }
}
