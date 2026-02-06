package services.actions;

import entities.Member;
import services.MembershipFactory;
import repositories.Repositories.MemberRepository;
import repositories.Repositories.MembershipTypeRepository;
import repositories.implementation.MemberRepositoryImpl;
import repositories.implementation.MembershipTypeRepositoryImpl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class Registration {
    private final MemberRepository memberRepo;
    private final MembershipTypeRepository typeRepo;
    private final Scanner scanner = new Scanner(System.in);

    public Registration() throws SQLException {
        this.memberRepo = new MemberRepositoryImpl();
        this.typeRepo = new MembershipTypeRepositoryImpl();
    }

    public void execute() {
        System.out.println("\n--- MEMBER REGISTRATION ---");
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Membership Type  with their ID: ");
        int typeId = scanner.nextInt();
        scanner.nextLine();

        try {
            Date expiryDate = MembershipFactory.calculateExpiryDate(typeId);
            Member newMember = new Member.Builder()
                    .setName(name)
                    .setEmail(email)
                    .setMembershipTypeId(typeId)
                    .setExpiryDate(expiryDate)
                    .build();

            memberRepo.add(newMember);
            System.out.println("Registration successful! Expiry Date: " + expiryDate);

        } catch (Exception e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }
}