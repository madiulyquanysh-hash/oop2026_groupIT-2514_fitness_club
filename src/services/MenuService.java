package services;

import repositories.implementation.*;
import services.actions.Booking;
import services.actions.FindMember;
import services.actions.Registration;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuService {
    private final Connection connection;
    private final MemberRepositoryImpl memberRepo;
    private final FitnessClassRepositoryImpl classRepo;
    private final BookingRepositoryImpl bookingRepo;
    private final MembershipTypeRepositoryImpl membershipRepo;
    private final Scanner scanner = new Scanner(System.in);

    public MenuService(Connection connection) throws SQLException {
        this.connection = connection;
        this.memberRepo = new MemberRepositoryImpl();
        this.classRepo = new FitnessClassRepositoryImpl();
        this.bookingRepo = new BookingRepositoryImpl();
        this.membershipRepo = new MembershipTypeRepositoryImpl();
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- FITNESS CLUB MENU ---");
            System.out.println("1. View All Members");
            System.out.println("2. View All Membership Types");
            System.out.println("3. View All Classes");
            System.out.println("4. Register New Member");
            System.out.println("5. Book a Class");
            System.out.println("6. Find Member");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> viewAllMembers();
                    case 2 -> viewAllMembershipTypes();
                    case 3 -> viewAllClasses();
                    case 4 -> registerNewMember();
                    case 5 -> bookAClass();
                    case 6 -> findAMember();
                    case 0 -> running = false;
                    default -> System.out.println("Invalid option!");
                }
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }
        }
    }

    private void viewAllMembers() throws SQLException {
        memberRepo.getAll().forEach(System.out::println);
    }

    private void viewAllMembershipTypes() throws SQLException {
        membershipRepo.getAll().forEach(System.out::println);
    }

    private void viewAllClasses() throws SQLException {
        classRepo.getAllClasses().forEach(System.out::println);
    }
    private void registerNewMember() {
        try {
            Registration registrationAction = new Registration();
            registrationAction.execute();
        } catch (SQLException e) {
            System.out.println("Registration error: " + e.getMessage());
        }
    }

    private void bookAClass() {
        try {
            Booking bookingAction = new Booking();
            bookingAction.execute();
        } catch (SQLException e) {
            System.out.println("Booking error: " + e.getMessage());
        }
    }

    private void findAMember() {
        try {
            FindMember findMemberAction = new FindMember(memberRepo);
            findMemberAction.execute();
        } catch (SQLException e) {
            System.out.println("Find member error: " + e.getMessage());
        }
    }
}