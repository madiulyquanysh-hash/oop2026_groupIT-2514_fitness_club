package services.Actions;

import java.sql.*;

public class ViewAll {
    private final Connection conn;

    public ViewAll(Connection conn) { this.conn = conn; }

    public void show(int option) {
        String query = switch (option) {
            case 1 -> "SELECT * FROM members";
            case 2 -> "SELECT * FROM membership_types";
            case 3 -> "SELECT * FROM classes";
            default -> "";
        };

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("\n--- Data List ---");
            while (rs.next()) {
                if (option == 1) System.out.println(rs.getString("name") + " | " + rs.getString("email"));
                if (option == 2) System.out.println(rs.getString("name") + " | " + rs.getString("price"));
                if (option == 3) System.out.println(rs.getString("title") + " - " + rs.getString("instructor"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }
}