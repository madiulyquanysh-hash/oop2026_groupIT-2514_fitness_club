package services.actions;

import entities.Member;
import repositories.Repositories.MemberRepository;
import repositories.Repositories.MembershipTypeRepository;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class Registration {
    private final MemberRepository memberRepo;
    private final MembershipTypeRepository membershipRepo;
    private final Scanner scanner = new Scanner(System.in);
    public Registration(MemberRepository memberRepo, MembershipTypeRepository membershipRepo) {
        this.memberRepo = memberRepo;
        this.membershipRepo = membershipRepo;
    }

    public void show() {
        System.out.println("\n--- MEMBER REGISTRATION ---");
        System.out.print("Enter Full Name: ");
        String fullName = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Membership Type: ");
        int typeId = scanner.nextInt();
        scanner.nextLine();

        try {
            LocalDate expiryLocalDate;
            switch (typeId) {
                case 1, 3 -> {
                    expiryLocalDate = LocalDate.now().plusMonths(1);
                }
                case 2, 4, 5 -> {
                    expiryLocalDate = LocalDate.now().plusYears(1);
                }
                default -> {
                    System.out.println("Unknown ID. Defaulting to 30 days with Standart-1 membership type.");
                    expiryLocalDate = LocalDate.now().plusDays(30);
                }
            }

            Date expiryDate = Date.valueOf(expiryLocalDate);
            Member newMember = new Member(0, fullName, email, typeId, null, expiryDate);

            memberRepo.addMember(newMember);
            System.out.println("Member registered! Expiry date set to: " + expiryDate);

        } catch (Exception e) {
            System.out.println("Registration failed: " + e.getMessage());
        }

    }
}