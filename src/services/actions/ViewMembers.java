package services.actions;

import entities.Member;
import exceptions.MembershipExpiredException;
import repositories.Repositories.MemberRepository;
import java.time.LocalDate;
import java.util.List;

public class ViewMembers {
    private final MemberRepository memberRepo;
    public ViewMembers(MemberRepository memberRepo) {
        this.memberRepo = memberRepo;
    }
    public void show() {
        try {
            System.out.println("\n--- MEMBERS TABLE ---");
            List<Member> members = memberRepo.getAllMembers();
            LocalDate today = LocalDate.now();

            if (members.isEmpty()) {
                System.out.println("No members found.");
                return;
            }

            for (Member m : members) {
                String status = "[ACTIVE]";
                try {
                    if (m.getExpiryDate() != null) {
                        LocalDate expiryDate = m.getExpiryDate().toLocalDate();
                        if (today.isAfter(expiryDate)) {
                            throw new MembershipExpiredException("EXPIRED on " + expiryDate);
                        }
                    }
                } catch (MembershipExpiredException e) {
                    status = "!!!   " + e.getMessage() + "   !!!";
                }
                System.out.println("ID: " + m.getId() +
                        " | Name: " + m.getName() +
                        " | Email: " + m.getEmail() +
                        " | Membership type: " + m.getMembershipTypeId() +
                        " | Expiry date: " + m.getExpiryDate() +
                        " | Status: " + status);
            }
        } catch (Exception e) {
            System.out.println("Error fetching members: " + e.getMessage());
        }
    }
}