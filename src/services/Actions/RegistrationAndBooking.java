package services.Actions;

import java.sql.*;
import java.util.Scanner;

public class RegistrationAndBooking {
    private final Connection conn;
    private final Scanner scanner = new Scanner(System.in);

    public RegistrationAndBooking(Connection conn) { this.conn = conn; }

    public void handle(int option) {
        if (option == 4) registerMember();
        if (option == 5) bookClass();
    }

    private void registerMember() {
        System.out.print("Enter name: "); String name = scanner.nextLine();
        System.out.print("Enter email: "); String email = scanner.nextLine();
        System.out.println("Enter Membership Type: ");
        System.out.println("1-Standard");
        System.out.println("2-Premium");
        System.out.println("3-Monthly quota subscription");
        System.out.println("4-YEarly quota subscription");
        System.out.println("--->");
        int typeId = scanner.nextInt();

        String sql = "INSERT INTO members (name, email, membership_type_id) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setInt(3, typeId);
            pstmt.executeUpdate();
            System.out.println("Member registered successfully!");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    private void bookClass() {
        System.out.println("--- Fast Registration & Booking ---");
        System.out.print("Enter Name: "); String name = scanner.nextLine();
        System.out.print("Enter Email: "); String email = scanner.nextLine();
        System.out.println("Enter Membership Type: ");
        System.out.println("1-Standard");
        System.out.println("2-Premium");
        System.out.println("3-Monthly quota subscription");
        System.out.println("4-YEarly quota subscription");
        System.out.println("--->"); int typeId = scanner.nextInt();
        System.out.println("Choose Instructor: ");
        System.out.println("1 = Fitness" + "|" + "Without instruc");
        System.out.println("2 = Fitness" + "|" + "Aigerim");
        System.out.println("3 = Boxing" + "|" + "Nurmahammed");
        System.out.println("4 = Dance" + "|" + "Miras");
        System.out.println("5 = Yoga" + "|" + "Alam");
        System.out.println("6 = Pilates" + "|" + "Ismail");
        int classId = scanner.nextInt();
        scanner.nextLine();

        String sqlMember = "INSERT INTO members (name, email, membership_type_id) VALUES (?, ?, ?) RETURNING id";
        String sqlBooking = "INSERT INTO class_bookings (member_id, class_id) VALUES (?, ?)";

        try {
            int newMemberId = -1;
            try (PreparedStatement pstmt1 = conn.prepareStatement(sqlMember)) {
                pstmt1.setString(1, name);
                pstmt1.setString(2, email);
                pstmt1.setInt(3, typeId);
                ResultSet rs = pstmt1.executeQuery();
                if (rs.next()) newMemberId = rs.getInt(1);
            }

            if (newMemberId != -1) {
                try (PreparedStatement pstmt2 = conn.prepareStatement(sqlBooking)) {
                    pstmt2.setInt(1, newMemberId);
                    pstmt2.setInt(2, classId);
                    pstmt2.executeUpdate();
                    System.out.println("Success! Member registered and booked.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}