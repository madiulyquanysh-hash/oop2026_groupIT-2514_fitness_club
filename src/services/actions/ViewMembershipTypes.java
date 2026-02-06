package services.actions;

import entities.MembershipType;
import repositories.Repositories.MembershipTypeRepository;
import repositories.implementation.MembershipTypeRepositoryImpl;
import java.sql.SQLException;
import java.util.List;

public class ViewMembershipTypes {
    private final MembershipTypeRepository membershipRepo;

    public ViewMembershipTypes() throws SQLException {
        this.membershipRepo = new MembershipTypeRepositoryImpl();
    }

    public void show() {
        try {
            System.out.println("\n--- MEMBERSHIP TYPES TABLE ---");
            List<MembershipType> types = membershipRepo.getAll();

            if (types.isEmpty()) {
                System.out.println("No membership types found.");
                return;
            }

            types.stream().forEach(t -> System.out.println(
                    "ID: " + t.getId() +
                            " | Plan: " + t.getName() +
                            " | Price: " + t.getPrice() +
                            " | Validity: " + t.getDurationDays() + " days"
            ));

        } catch (Exception e) {
            System.out.println("Error loading membership types: " + e.getMessage());
        }
    }
}