package repositories.implementation;

import entities.FitnessClass;
import repositories.Repositories.FitnessClassRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FitnessClassRepositoryImpl implements FitnessClassRepository {
    private final Connection connection;

    public FitnessClassRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<FitnessClass> getAllClasses() throws SQLException {
        List<FitnessClass> classes = new ArrayList<>();
        String sql = "SELECT * FROM classes";

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                classes.add(new FitnessClass(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("instructor"),
                        rs.getDouble("cost"),
                        rs.getInt("capacity"),
                        rs.getInt("remaining_capacity")
                ));
            }
        }
        return classes;
    }

    @Override
    public FitnessClass getClassById(int id) throws SQLException {
        String sql = "SELECT * FROM classes WHERE id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new FitnessClass(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("instructor"),
                        rs.getDouble("cost"),
                        rs.getInt("capacity"),
                        rs.getInt("remaining_capacity")
                );
            }
        }
        return null;
    }

    @Override
    public void updateCapacity(int classId, int newCapacity) throws SQLException {
        String sql = "UPDATE classes SET remaining_capacity = ? WHERE id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, newCapacity);
            st.setInt(2, classId);
            st.executeUpdate();
        }
    }
}