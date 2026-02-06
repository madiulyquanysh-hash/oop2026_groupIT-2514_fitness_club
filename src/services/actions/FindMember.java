package services.actions;

import entities.Member;
import exceptions.MemberNotFoundException;
import repositories.Repositories.MemberRepository;
import java.util.List;
import java.util.Scanner;

public class FindMember {
    private final MemberRepository memberRepo;
    private final Scanner scanner = new Scanner(System.in);

    public FindMember(MemberRepository memberRepo) {
        this.memberRepo = memberRepo;
    }
//new
    public void show() {
        System.out.println("\n--- FIND MEMBER ---");
        System.out.print("Enter Member's Name: ");
        String nameQuery = scanner.nextLine();

        try {
            List<Member> foundMembers = memberRepo.searchByName(nameQuery);

            if (foundMembers.isEmpty()) {
                throw new MemberNotFoundException("No member found matching: " + nameQuery);
            }

            for (Member m : foundMembers) {
                System.out.println("ID: " + m.getId() +
                        " | Name: " + m.getName() +
                        " | Email: " + m.getEmail() +
                        " | Membership type: " + m.getMembershipTypeId() +
                        " | Expiry date: " + m.getExpiryDate()
                        );
            }

        } catch (MemberNotFoundException e) {
            System.out.println("Search Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}