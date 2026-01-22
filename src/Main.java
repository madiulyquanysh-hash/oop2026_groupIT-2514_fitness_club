import edu.aitu.oop3.db.data.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import services.MenuService;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Connecting to Supabase...");

        try (Connection connection = DatabaseConnection.getConnection()) {
            System.out.println("Connected successfully!");

            String sqlTime = "SELECT CURRENT_TIMESTAMP";
            try (PreparedStatement stmt = connection.prepareStatement(sqlTime);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Database time: " + rs.getTimestamp(1));
                }
            }

            MenuService menu = new MenuService(connection);
            menu.start();

        } catch (SQLException e) {
            System.out.println("Error while connecting to database:");
            e.printStackTrace();
        }
    }
}