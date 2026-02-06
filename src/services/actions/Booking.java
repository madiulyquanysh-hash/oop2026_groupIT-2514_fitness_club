package services.actions;

import repositories.Repositories.BookingRepository;
import repositories.Repositories.FitnessClassRepository;
import repositories.Repositories.MemberRepository;
import repositories.implementation.BookingRepositoryImpl;
import repositories.implementation.FitnessClassRepositoryImpl;
import repositories.implementation.MemberRepositoryImpl;
import java.sql.SQLException;
import java.util.Scanner;
import entities.Member;

public class Booking {
    private final MemberRepository memberRepo;
    private final FitnessClassRepository classRepo;
    private final BookingRepository bookingRepo;
    private final Scanner scanner = new Scanner(System.in);

    public Booking() throws SQLException {
        this.memberRepo = new MemberRepositoryImpl();
        this.classRepo = new FitnessClassRepositoryImpl();
        this.bookingRepo = new BookingRepositoryImpl();
    }

    public void execute() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter member email: ");
        String email = scanner.nextLine();

        Member member = memberRepo.findByEmail(email);

        if (member == null) {
            System.out.println("Error: Member not found!");
            return;
        }

        if (!member.isActive()) {
            System.out.println("Error: Membership EXPIRED.");
            return;
        }

        System.out.print("Enter Class ID: ");
        int classId = scanner.nextInt();

        String info= bookingRepo.addBooking(member.getId(), classId);

        System.out.println("------------------------------------");
        System.out.println("Booking successful!");
        System.out.println("Section: " + info);
        System.out.println("------------------------------------");
    }
}