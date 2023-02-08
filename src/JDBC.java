import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class JDBC {
    public List<Automobile> getAutomobiles() {
        List<Automobile> automobiles = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tuttorial",
                    "postgres", "mukha");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM automobiles");

            while (rs.next()) {
                boolean isAutomatic = rs.getBoolean("isAutomatic");
                int releaseYear = rs.getInt("releaseYear");
                boolean isNew = rs.getBoolean("isNew");
                String color = rs.getString("color");
                double price = rs.getDouble("price");
                double volumeOfEngine = rs.getDouble("volumeOfEngine");
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