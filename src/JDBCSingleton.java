import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

public class JDBCSingleton {
    public static volatile JDBCSingleton jdbc;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/tuttorial";
    private static final String USER = "postgres";
    private static final String PASS = "mukha";

    private JDBCSingleton() {}

    public static JDBCSingleton getInstance() {
        JDBCSingleton result = jdbc;
        if (result == null) {
            synchronized (JDBCSingleton.class) {
                if (jdbc == null) {
                    jdbc = new JDBCSingleton();
                }
            }
        }
        return result;
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
        return connection;
    }
    public static void changeAuto (int carId, boolean isNew, boolean isAutomatic, int releaseYear, int price, String color,
                                   double volumeOfEngine, String brand, String model) {
        try (Connection connection = getConnection()) {
            String sql = "UPDATE automobiles SET is_automatic = ?, release_year = ?, is_new = ?, color = ?, price = ?, volume_of_engine = ?, brand = ?, model = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, isAutomatic);
            statement.setInt(2, releaseYear);
            statement.setBoolean(3, isNew);
            statement.setString(4, color);
            statement.setInt(5, price);
            statement.setDouble(6, volumeOfEngine);
            statement.setString(7, brand);
            statement.setString(8, model);
            statement.setInt(9, carId);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Car information updated successfully.");
            } else {
                System.out.println("Unable to update car information.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void addCar(boolean isAutomatic, int releaseYear, boolean isNew, String color,
                              int price, double volumeOfEngine, String brand, String model) {
        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO automobiles (is_automatic, release_year, is_new, color, price, volume_of_engine, brand, model) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, isAutomatic);
            statement.setInt(2, releaseYear);
            statement.setBoolean(3, isNew);
            statement.setString(4, color);
            statement.setInt(5, price);
            statement.setDouble(6, volumeOfEngine);
            statement.setString(7, brand);
            statement.setString(8, model);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean registerUser(String username, String password, boolean is_admin) throws AuthenticationException {
        try {
            String checkUserSql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement checkUserStmt = getConnection().prepareStatement(checkUserSql);
            checkUserStmt.setString(1, username);
            ResultSet checkUserResult = checkUserStmt.executeQuery();

            if (checkUserResult.next()) {
                throw new AuthenticationException("Username already exists");
            }

            if (password.length() < 8) {
                throw new AuthenticationException("Password should have at least 8 characters");
            }

            if (password.matches(".*[@#$%^&*].*")) {
                throw new AuthenticationException("Password should not contain @#$%^&*");
            }

            User newUser = new User(username, password, is_admin);
            String insertUserSql = "INSERT INTO users (username, password, is_admin) VALUES (?, ?, ?)";
            PreparedStatement insertUserStmt = getConnection().prepareStatement(insertUserSql);
            insertUserStmt.setString(1, newUser.getUsername());
            insertUserStmt.setString(2, newUser.getPassword());
            insertUserStmt.setBoolean(3, newUser.getIsAdmin());
            insertUserStmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new AuthenticationException("Error registering user", e);
        }
    }

    public boolean loginUser(String username, Scanner scanner, int option) {
        try {
            String checkUserSql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement checkUserStmt = getConnection().prepareStatement(checkUserSql);
            checkUserStmt.setString(1, username);
            ResultSet checkUserResult = checkUserStmt.executeQuery();
            if (!checkUserResult.next()) {
                return false;
            }
            System.out.print("Enter your password: ");
            String password = scanner.next();
            if (!checkUserResult.getString("password").equals(password)) {
                return false;
            }
            if (option == 2 && !checkUserResult.getBoolean("is_admin")) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}


