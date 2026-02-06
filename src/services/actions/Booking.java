package services.actions;

import entities.Member;
import entities.FitnessClass;
import exceptions.MemberNotFoundException;
import exceptions.ClassFullException;
import exceptions.MembershipExpiredException;
import repositories.Repositories.MemberRepository;
import repositories.Repositories.FitnessClassRepository;
import repositories.Repositories.BookingRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class  Booking {
    private final MemberRepository memberRepo;
    private final FitnessClassRepository classRepo;
    private final BookingRepository bookingRepo;
    private final Scanner scanner = new Scanner(System.in);
    public Booking(MemberRepository memberRepo, FitnessClassRepository classRepo, BookingRepository bookingRepo) {
        this.memberRepo = memberRepo;
        this.classRepo = classRepo;
        this.bookingRepo = bookingRepo;
    }

    public void show() {
        try {
            System.out.println("\n--- BOOKING A CLASS ---");
            System.out.print("Enter your Email: ");
            String email = scanner.nextLine();
            Member member = memberRepo.getMemberByEmail(email);

            if (member == null) {
                throw new MemberNotFoundException("Member with email - " + email + " didn't found!");
            }
            if (member.getExpiryDate() != null && LocalDate.now().isAfter(member.getExpiryDate().toLocalDate())) {
                throw new MembershipExpiredException("Your membership expired on " + member.getExpiryDate());
            }

            List<FitnessClass> classes = classRepo.getAllClasses();
            if (classes.isEmpty()) {
                System.out.println("No classes available at the moment.");
                return;
            }

            for (FitnessClass c : classes) {
                System.out.println("ID: " + c.getId() +
                        " | Class: " + c.getTitle() +
                        " | Instructor: " + c.getInstructor() +
                        " | Price: " + c.getCost() +
                        " | Slots: " + c.getRemainingCapacity() + "/" + c.getCapacity());
            }

            System.out.print("Choose the class with ID: ");
            int classId = scanner.nextInt();
            scanner.nextLine();
            FitnessClass fitnessClass = classRepo.getClassById(classId);

            if (fitnessClass == null) {
                System.out.println("Class not found!");
                return;
            }
            if (fitnessClass.getRemainingCapacity() <= 0) {
                throw new ClassFullException("No seats left in this class!!!");
            }

            bookingRepo.createBooking(member.getId(), classId);
            System.out.println("Success! You are booked for: " + fitnessClass.getTitle() + " | Instructor:" + fitnessClass.getInstructor());

        } catch (MemberNotFoundException | ClassFullException | MembershipExpiredException e) {
            System.out.println("Booking failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}