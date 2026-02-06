package repositories.implementation;

import edu.aitu.oop3.db.data.DatabaseConnection;
import entities.FitnessClass;
import repositories.Repositories.FitnessClassRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FitnessClassRepositoryImpl implements FitnessClassRepository {
    private final Connection connection;

    public FitnessClassRepositoryImpl() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public FitnessClass getClassById(int id) throws SQLException {
        String sql = "SELECT * FROM classes WHERE id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) return createFromResultSet(rs);
            }
        }
        return null;
    }

    @Override
    public List<FitnessClass> getAllClasses() throws SQLException {
        List<FitnessClass> classes = new ArrayList<>();
        String sql = "SELECT * FROM classes";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                classes.add(createFromResultSet(rs));
            }
        }
        return classes;
    }

    @Override
    public List<FitnessClass> getAll() throws SQLException {
        return getAllClasses();
    }

    @Override
    public FitnessClass getById(int id) throws SQLException {
        return getClassById(id);
    }

    @Override
    public void updateCapacity(int classId, int newCapacity) throws SQLException {
        String sql = "UPDATE classes SET capacity = ? WHERE id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, newCapacity);
            st.setInt(2, classId);
            st.executeUpdate();
        }
    }

    @Override
    public void add(FitnessClass entity) throws SQLException {
        String sql = "INSERT INTO classes (title, instructor, capacity) VALUES (?, ?, ?)";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, entity.getTitle());
            st.setString(2, entity.getInstructor());
            st.setInt(3, entity.getCapacity());
            st.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM classes WHERE id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        }
    }

    private FitnessClass createFromResultSet(ResultSet rs) throws SQLException {
        return new FitnessClass.Builder()
                .setId(rs.getInt("id"))
                .setTitle(rs.getString("title"))
                .setInstructor(rs.getString("instructor"))
                .setCapacity(rs.getInt("capacity"))
                .setCost(rs.getDouble("cost"))
                .setRemainingCapacity(rs.getInt("remaining_capacity"))
                .build();
    }
}