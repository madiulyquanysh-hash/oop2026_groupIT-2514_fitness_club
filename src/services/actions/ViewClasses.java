package services.actions;

import entities.FitnessClass;
import repositories.Repositories.FitnessClassRepository;
import java.util.List;

public class ViewClasses {
    private final FitnessClassRepository classRepo;

    public ViewClasses(FitnessClassRepository classRepo) {
        this.classRepo = classRepo;
    }

    public void show() {
        try {
            System.out.println("\n--- FITNESS CLASS TABLE ---");
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
                        " | Capacity: " + c.getRemainingCapacity() + "/" + c.getCapacity());
            }
        } catch (Exception e) {
            System.out.println("Error displaying schedule: " + e.getMessage());
        }
    }
}