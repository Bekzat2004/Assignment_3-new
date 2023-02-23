import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Functionality {
    List<Automobile> automobiles = List.of(
            new Automobile(true, 2022, true, "silver", 35000, 3, "Toyota", "Camry"),
            new Automobile(false, 2003, false, "black", 10000, 2, "Lada", "VAZ"),
            new Automobile(true, 2020, false, "red", 20000, 2, "Honda", "Civic"),
            new Automobile(false, 2019, true, "black", 50000, 4, "Jeep", "Wrangler"),
            new Automobile(true, 2022, false, "silver", 60000, 4, "Mercedes", "Benz"),
            new Automobile(true, 2017, false, "white", 35000, 3, "BMW", "M5"),
            new Automobile(false, 2011, false, "black", 17000, 2, "Opel", "Astra"),
            new Automobile(true, 2015, false, "white", 47000, 3, "Lexus", "Sedan"),
            new Automobile(false, 2012, false, "black", 9000, 2, "Toyota", "Corolla"),
            new Automobile(true, 2018, true, "white", 17000, 3, "Mercedes", "AMG-C63")
    );
    public static void findCar() {
        Scanner scanner = new Scanner(System.in);
        TransmissionBoxFilter filterTransmission = new TransmissionBoxFilter();
        ConditionFilter filterCondition = new ConditionFilter();
        ReleaseYearFilter filterYear = new ReleaseYearFilter();
        PriceRangeFilter filterPrice = new PriceRangeFilter();
        VolumeOfEngineFilter filterVolume = new VolumeOfEngineFilter();
        ColorFilter filterColor = new ColorFilter();
        BrandFilter filterBrand = new BrandFilter();
        ModelFilter filterModel = new ModelFilter();
        System.out.println("You should follow instructions to find a car.");
        inputCondition(scanner, filterCondition);
        inputTransmission(scanner, filterTransmission);
        inputReleaseYearRange(scanner, filterYear);
        inputPriceRange(scanner, filterPrice);
        inputVolume(scanner, filterVolume);
        inputColor(scanner, filterColor);
        inputBrand(scanner, filterBrand);
        inputModel(scanner, filterModel);
    }

    public static void publishCar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Follow the instructions to publish car.");
        System.out.println("Choose the condition of the car:\nTrue - new\nFalse - used");
        boolean isNew = scanner.nextBoolean();
        System.out.println("Choose the transmission:\nTrue - automatic\nFalse - mechanic");
        boolean isAutomatic = scanner.nextBoolean();
        System.out.println("Enter a year of release.");
        int releaseYear = scanner.nextInt();
        System.out.println("Enter a price.");
        int price = scanner.nextInt();
        System.out.println("Enter a color.");
        String color = scanner.next();
        System.out.println("Enter a volume of engine.");
        double volumeOfEngine = scanner.nextDouble();
        System.out.println("Enter the brand of car.");
        String brand = scanner.next();
        System.out.println("Enter the model of car.");
        String model = scanner.next();
        JDBC.addCar(isAutomatic, releaseYear, isNew, color, price, volumeOfEngine, brand, model);
        System.out.println("Car successfully published.");
    }
    public static void updateCar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID of the car to update:");
        int carId = scanner.nextInt();
        System.out.println("Enter the new information about the car.");
        System.out.println("Choose the condition of the car:\nTrue - new\nFalse - used");
        boolean isNew = scanner.nextBoolean();
        System.out.println("Choose the transmission:\nTrue - automatic\nFalse - mechanic");
        boolean isAutomatic = scanner.nextBoolean();
        System.out.println("Enter a year of release.");
        int releaseYear = scanner.nextInt();
        System.out.println("Enter a price.");
        int price = scanner.nextInt();
        System.out.println("Enter a color.");
        String color = scanner.next();
        System.out.println("Enter a volume of engine.");
        double volumeOfEngine = scanner.nextDouble();
        System.out.println("Enter the brand of car.");
        String brand = scanner.next();
        System.out.println("Enter the model of car.");
        String model = scanner.next();
        JDBC.changeAuto(carId, isNew, isAutomatic, releaseYear, price, color, volumeOfEngine, brand, model);
    }
    public static void addUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the new user information.");
        System.out.println("Enter username:");
        String username = scanner.next();
        System.out.println("Enter password:");
        String password = scanner.next();
        System.out.println("Is the user an admin? (true/false):");
        boolean isAdmin = scanner.nextBoolean();
        JDBC jdbc = new JDBC();
        try (Connection connection = jdbc.getConnection()) {
            String sql = "INSERT INTO users (username, password, is_admin) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setBoolean(3, isAdmin);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("User added successfully.");
            } else {
                System.out.println("Unable to add user.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void showCar() {
        try {
            JDBC jdbc = new JDBC();
            String selectCarsSql = "SELECT * FROM automobiles";
            PreparedStatement selectCarsStmt = jdbc.getConnection().prepareStatement(selectCarsSql);
            ResultSet selectCarsResult = selectCarsStmt.executeQuery();
            System.out.println("+-------+------------+--------------+---------+------------+---------+-------+--------------+--------------------+");
            System.out.println("|   ID  |  Condition | Transmission |   Year  |    Price   |  Color  | Engine|     Brand    |        Model       |");
            System.out.println("+-------+------------+--------------+---------+------------+---------+-------+--------------+--------------------+");
            while (selectCarsResult.next()) {
                int id = selectCarsResult.getInt("id");
                boolean is_new = selectCarsResult.getBoolean("is_new");
                boolean is_automatic = selectCarsResult.getBoolean("is_automatic");
                int release_year = selectCarsResult.getInt("release_year");
                int price = selectCarsResult.getInt("price");
                String color = selectCarsResult.getString("color");
                double volume_of_engine = selectCarsResult.getDouble("volume_of_engine");
                String brand = selectCarsResult.getString("brand");
                String model = selectCarsResult.getString("model");
                String condition = is_new ? "New" : "Used";
                String transmission = is_automatic ? "Automatic" : "Mechanic";
                System.out.format("| %5d | %10s | %12s | %7d | %10d | %7s | %5.1f | %12s | %18s |\n", id, condition, transmission, release_year, price, color, volume_of_engine, brand, model);
                System.out.println("+-------+------------+--------------+---------+------------+---------+-------+--------------+--------------------+");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getUserInfo() {
        try {
            JDBC jdbc = new JDBC();
            String selectUsersSql = "SELECT username, password, is_admin FROM users";
            PreparedStatement selectUsersStmt = jdbc.getConnection().prepareStatement(selectUsersSql);
            ResultSet selectUsersResult = selectUsersStmt.executeQuery();
            System.out.println("+--------------+--------------+---------+");
            System.out.println("|   Username   |   Password   | Is Admin|");
            System.out.println("+--------------+--------------+---------+");
            while (selectUsersResult.next()) {
                String username = selectUsersResult.getString("username");
                String password = selectUsersResult.getString("password");
                boolean is_admin = selectUsersResult.getBoolean("is_admin");
                System.out.format("| %12s | %12s | %7s |\n", username, password, is_admin);
                System.out.println("+--------------+--------------+---------+");
            }
        } catch (SQLException e) {
        }
    }

    private static void inputReleaseYearRange(Scanner scanner, ReleaseYearFilter filter) {
        System.out.println("Enter the range of release.");
        System.out.print("From year: ");
        filter.setFromYear(scanner.nextInt());
        System.out.print("To year: ");
        filter.setToYear(scanner.nextInt());
    }

    public static void inputTransmission(Scanner scanner, TransmissionBoxFilter filter) {
        System.out.println("Choose the transmission:\nTrue - automatic\nFalse - mechanic");
        filter.setAutomatic(scanner.nextBoolean());
    }

    public static void inputCondition(Scanner scanner, ConditionFilter filter) {
        System.out.println("Choose the condition of the car:\nTrue - new\nFalse - used");
        filter.setCondition(scanner.nextBoolean());
    }

    public static void inputColor(Scanner scanner, ColorFilter filter) {
        System.out.println("Write a color of car by lower case letters.");
        filter.setColor(scanner.next());
    }

    public static void inputPriceRange(Scanner scanner, PriceRangeFilter filter) {
        System.out.println("Enter the range of price.");
        System.out.println("From minimum amount:");
        filter.setFrom(scanner.nextInt());
        System.out.println("To maximum amount:");
        filter.setTo(scanner.nextInt());
    }

    public static void inputVolume(Scanner scanner, VolumeOfEngineFilter filter) {
        System.out.println("Enter the volume of engine.");
        filter.setVolumeOfEngine(scanner.nextDouble());
    }

    public static void inputBrand(Scanner scanner, BrandFilter filter) {
        System.out.println("Enter the brand.");
        filter.setBrand(scanner.next());
    }

    public static void inputModel(Scanner scanner, ModelFilter filter) {
        System.out.println("Enter the model.");
        filter.setModel(scanner.next());
    }
}
