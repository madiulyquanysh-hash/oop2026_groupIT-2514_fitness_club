package services;

import repositories.Repositories.BookingRepository;
import repositories.Repositories.FitnessClassRepository;
import repositories.Repositories.MemberRepository;
import repositories.Repositories.MembershipTypeRepository;
import repositories.implementation.*;
import services.actions.*;
import java.sql.Connection;
import java.util.Scanner;

public class MenuService {
    private final Connection connection;
    private final Scanner scanner = new Scanner(System.in);
    private final MemberRepository memberRepo;
    private final FitnessClassRepository classRepo;
    private final BookingRepository bookingRepo;
    private final MembershipTypeRepository membershipRepo;

    public MenuService(Connection connection) {
        this.connection = connection;
        this.memberRepo = new MemberRepositoryImpl(connection);
        this.classRepo = new FitnessClassRepositoryImpl(connection);
        this.bookingRepo = new BookingRepositoryImpl(connection);
        this.membershipRepo = new MembershipTypeRepositoryImpl(connection);
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
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> new ViewMembers(memberRepo).show();
                case 2 -> new ViewMembershipTypes(membershipRepo).show();
                case 3 -> new ViewClasses(classRepo).show();
                case 4 -> new Registration(memberRepo, membershipRepo).show();
                case 5 -> new Booking(memberRepo, classRepo, bookingRepo).show();
                case 6 -> new FindMember(memberRepo).show();
                case 0 -> running = false;
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}