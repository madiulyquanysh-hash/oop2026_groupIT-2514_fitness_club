package services.actions;

import entities.Member;
import exceptions.MemberNotFoundException;
import repositories.Repositories.MemberRepository;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class FindMember {
    private final MemberRepository memberRepo;
    private final Scanner scanner = new Scanner(System.in);

    public FindMember(MemberRepository memberRepo) {
        this.memberRepo = memberRepo;
    }

    public void execute() throws SQLException {
        System.out.println("\n--- FIND MEMBER ---");
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();

        try {
            List<Member> foundMembers = memberRepo.getAll().stream()
                    .filter(m -> m.getName().toLowerCase().contains(name.toLowerCase()))
                    .toList();

            if (foundMembers.isEmpty()) {
                throw new MemberNotFoundException("No members found with name: " + name);
            }

            foundMembers.forEach(m -> System.out.println("ID: " + m.getId() +
                    " | Name: " + m.getName() +
                    " | Email: " + m.getEmail() +
                    " | Membership type: " + m.getMembershipTypeId() +
                    " | Expiry date: " + m.getExpiryDate()));

        } catch (MemberNotFoundException e) {
            System.out.println("Search Error: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}