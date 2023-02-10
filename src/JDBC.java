import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class JDBC {
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/tuttorial";
    private static final String USER = "postgres";
    private static final String PASS = "mukha";
    public List<Automobile> getAutomobiles() {
        List<Automobile> automobiles = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM auto");

            while (rs.next()) {
                boolean isAutomatic = rs.getBoolean("is_automatic");
                int releaseYear = rs.getInt("release_year");
                boolean isNew = rs.getBoolean("is_new");
                String color = rs.getString("color");
                double price = rs.getDouble("price");
                double volumeOfEngine = rs.getDouble("volume_of_engine");
                String brand = rs.getString("brand");
                String model = rs.getString("model");
                automobiles.add(new Automobile(isAutomatic, releaseYear, isNew, color, price, volumeOfEngine, brand, model));

            }
            rs.close();
            stmt.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return automobiles;
    }
}
