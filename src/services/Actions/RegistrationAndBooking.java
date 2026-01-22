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
        System.out.print("Enter Membership Type ID (1-Standard, 2-Premium): ");
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
        System.out.println("Available classes and instructors:");

        System.out.print("Enter Class ID: "); int classId = scanner.nextInt();
        System.out.print("Enter Member ID: "); int memberId = scanner.nextInt();

        String sqlBooking = "INSERT INTO class_bookings (member_id, class_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlBooking)) {
            pstmt.setInt(1, memberId);
            pstmt.setInt(2, classId);
            pstmt.executeUpdate();
            System.out.println("Booking confirmed!");

        } catch (SQLException e) { e.printStackTrace(); }
    }
}