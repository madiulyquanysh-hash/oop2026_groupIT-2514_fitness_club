package repositories.Repositories;

import java.sql.SQLException;
import java.util.List;

public interface CrudRepository<T> {
    void add(T entity) throws SQLException;
    T getById(int id) throws SQLException;
    List<T> getAll() throws SQLException;
    void delete(int id) throws SQLException;

    static void printStatus() {
        System.out.println("[INFO]: Repository system is active and connected.");
    }

    default void logAction(String action, String details) {
        System.out.println("[LOG " + java.time.LocalDateTime.now() + "]: " + action + " -> " + details);
    }
}