import java.sql.*;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);
        JDBC jdbc = new JDBC();
        List<Automobile> automobiles = jdbc.getAutomobiles();
        for (Automobile automobile : automobiles) {
            System.out.println(automobile.getInfo());
        }
        TransmissionBoxFilter transmission = new TransmissionBoxFilter();
        ReleaseYearFilter releaseYear = new ReleaseYearFilter();
        automobiles = transmission.filter(automobiles);
        menu(input, automobiles);
    }

    public static void menu(Scanner scanner, List<Automobile> automobiles) throws SQLException {
        boolean isOpen = true;
        while (isOpen) {
            System.out.println("Choose an option by the number below:");
            System.out.println("1. Find a car");
            System.out.println("2. Publish new car");
            System.out.println("3. Exit");
            switch (scanner.nextInt()) {
                case 1 -> findCar();
                case 2 -> publishCar();
                case 3 -> isOpen = false;
                default -> System.out.println("Pick a number between 1 and 3");
            }
        }
    }

    private static void findCar() {
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
        JDBC jdbc = new JDBC();
        List<Automobile> automobiles = jdbc.getAutomobiles();
        List<Automobile> filteredAutomobiles = filterTransmission.filter(automobiles);
        filteredAutomobiles = filterCondition.filter(filteredAutomobiles);
        filteredAutomobiles = filterYear.filter(filteredAutomobiles);
        filteredAutomobiles = filterPrice.filter(filteredAutomobiles);
        filteredAutomobiles = filterVolume.filter(filteredAutomobiles);
        filteredAutomobiles = filterColor.filter(filteredAutomobiles);
        filteredAutomobiles = filterBrand.filter(filteredAutomobiles);
        filteredAutomobiles = filterModel.filter(filteredAutomobiles);
        for (Automobile automobile : filteredAutomobiles) {
            System.out.println(automobile);
        }
    }



    private static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/tuttorial";
        String username = "postgres";
        String password = "mukha";
        return DriverManager.getConnection(url, username, password);
    }

    private static void publishCar() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You should follow instructions to publish a car.");
        System.out.println("Write ID:");
        int id = scanner.nextInt();
        System.out.println("Choose the condition of the car:\nTrue - new\nFalse - used");
        boolean isNew = scanner.nextBoolean();
        System.out.println("Choose the transmission:\nTrue - automatic\nFalse - mechanic");
        boolean isAutomatic = scanner.nextBoolean();
        System.out.println("Enter a year.");
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
        Connection connection = getConnection();
        String sql = "INSERT INTO auto (id, is_automatic, release_year, is_new, color, price, volume_of_engine, brand, model) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.setInt(3, releaseYear);
        statement.setBoolean(4, isNew);
        statement.setString(5, color);
        statement.setInt(6, price);
        statement.setDouble(7, volumeOfEngine);
        statement.setString(8, brand);
        statement.setString(9, model);
        statement.setBoolean(2, isAutomatic);
        statement.executeUpdate();
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
