package services.actions;

import edu.aitu.oop3.db.data.DatabaseConnection;
import entities.FitnessClass;
import repositories.Repositories.FitnessClassRepository;
import repositories.implementation.FitnessClassRepositoryImpl;
import java.sql.SQLException;
import java.util.List;

public class ViewClasses {
    private final FitnessClassRepository classRepo;
    public ViewClasses() throws SQLException {
        this.classRepo = new FitnessClassRepositoryImpl();
    }

    public void show() {
        try {
            System.out.println("\n--- CLASSES TABLE ---");
            List<FitnessClass> classes = classRepo.getAll();
            classes.stream()
                    .filter(c -> c.getRemainingCapacity() > 0)
                    .forEach(c -> System.out.println("Class: " + c.getTitle() +
                            " | Instructor: " + c.getInstructor() +
                            " | Seats left: " + c.getRemainingCapacity()));

        } catch (SQLException e) {
            System.out.println("Error loading classes: " + e.getMessage());
        }
    }
}