import java.util.Scanner;

public class MenuUser {
    public static void menu(Scanner scanner )  {
        boolean isOpen = true;
        while (isOpen) {
            System.out.println("Welcome to Customer page!");
            System.out.println("1. Find a car");
            System.out.println("2. Publish new car");
            System.out.println("3. Browse all automobiles");
            System.out.println("4. Exit");
            switch (scanner.nextInt()) {
                case 1 -> Functionality.findCar();
                case 2 -> Functionality.publishCar();
                case 3 -> Functionality.showCar();
                case 4 -> isOpen = false;
                default -> System.out.println("Pick a number between 1 and 4");
            }
        }
    }
}
