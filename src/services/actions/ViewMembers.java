package services.actions;

import entities.Member;
import repositories.Repositories.MemberRepository;
import repositories.implementation.MemberRepositoryImpl;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ViewMembers {
    private final MemberRepository memberRepo;
    public ViewMembers() throws SQLException {
        this.memberRepo = new MemberRepositoryImpl();
    }

    public void show() {
        try {
            System.out.println("\n--- MEMBERS TABLE ---");
            List<Member> members = memberRepo.getAll();
            LocalDate today = LocalDate.now();

            if (members.isEmpty()) {
                System.out.println("No members found.");
                return;
            }
            members.stream().forEach(m -> {
                String status = "[ACTIVE]";
                if (m.getExpiryDate() != null) {
                    LocalDate expiryDate = m.getExpiryDate().toLocalDate();
                    if (today.isAfter(expiryDate)) {
                        status = "!!!   EXPIRED on " + expiryDate + "   !!!";
                    }
                }

                System.out.println("ID: " + m.getId() +
                        " | Name: " + m.getName() +
                        " | Email: " + m.getEmail() +
                        " | Status: " + status);
            });

        } catch (Exception e) {
            System.out.println("Error fetching members: " + e.getMessage());
        }
    }
}