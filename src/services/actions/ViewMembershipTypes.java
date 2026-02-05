package services.actions;

import entities.MembershipType;
import repositories.Repositories.MembershipTypeRepository;
import java.util.List;

public class ViewMembershipTypes {
    private final MembershipTypeRepository membershipRepo;
    public ViewMembershipTypes(MembershipTypeRepository membershipRepo) {
        this.membershipRepo = membershipRepo;
    }

    public void show() {
        try {
            System.out.println("\n--- MEMBERSHIP TYPES TABLE ---");
            List<MembershipType> types = membershipRepo.getAllTypes();
            if (types.isEmpty()) {
                System.out.println("No membership types found.");
                return;
            }
            for (MembershipType t : types) {
                System.out.println("ID: " + t.getId() +
                        " | Plan: " + t.getName() +
                        " | Price: " + t.getPrice() +
                        " | Validity: " + t.getDurationDays() + " mouth");
            }
        } catch (Exception e) {
            System.out.println("Error loading membership types: " + e.getMessage());
        }
    }
}
