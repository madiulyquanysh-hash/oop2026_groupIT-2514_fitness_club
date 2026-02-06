import edu.aitu.oop3.db.data.DatabaseConnection;
import services.MenuService;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Initializing Database Connection...");

        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            System.out.println("Connected to Database successfully!");

            MenuService menu = new MenuService(conn);
            menu.start();

        } catch (SQLException e) {
            System.err.println("CRITICAL ERROR: Could not establish database connection.");
            System.err.println("Details: " + e.getMessage());
        }
    }
}

