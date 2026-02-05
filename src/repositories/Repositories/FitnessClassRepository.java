package repositories.Repositories;

import entities.FitnessClass;
import java.sql.SQLException;
import java.util.List;

public interface FitnessClassRepository {
    List<FitnessClass> getAllClasses() throws SQLException;
    void updateCapacity(int classId, int newCapacity) throws SQLException;
    FitnessClass getClassById(int id) throws SQLException;
}