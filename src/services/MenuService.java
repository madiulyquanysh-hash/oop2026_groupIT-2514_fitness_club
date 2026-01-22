package services;

import edu.aitu.oop3.db.data.DatabaseConnection;
import java.sql.*;
import java.util.Scanner;
import services.Actions.ViewAll;
import services.Actions.RegistrationAndBooking;

public class MenuService {
    private final Connection connection;
    private final Scanner scanner;

    public MenuService(Connection connection) {
        this.connection = connection;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- FITNESS CLUB MENU ---");
            System.out.println("1. View all members");
            System.out.println("2. View all membership");
            System.out.println("3. View all classes");
            System.out.println("4. Register new member");
            System.out.println("5. book a fitness class");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1, 2, 3 -> new ViewAll(connection).show(choice);
                case 4, 5 -> new RegistrationAndBooking(connection).handle(choice);
                case 0 -> running = false;
            }
        }
    }
}