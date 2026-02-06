package repositories.implementation;

import edu.aitu.oop3.db.data.DatabaseConnection;
import entities.MembershipType;
import repositories.Repositories.MembershipTypeRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembershipTypeRepositoryImpl implements MembershipTypeRepository {
    private final Connection connection;

    public MembershipTypeRepositoryImpl() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void add(MembershipType entity) throws SQLException {
        String sql = "INSERT INTO membership_types (name, price, duration_days) VALUES (?, ?, ?)";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, entity.getName());
            st.setDouble(2, entity.getPrice());
            st.setInt(3, entity.getDurationDays());
            st.executeUpdate();
        }
    }

    @Override
    public List<MembershipType> getAll() throws SQLException {
        return getAllTypes();
    }

    @Override
    public List<MembershipType> getAllTypes() throws SQLException {
        List<MembershipType> types = new ArrayList<>();
        String sql = "SELECT * FROM membership_types";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                types.add(createFromResultSet(rs));
            }
        }
        return types;
    }

    @Override
    public MembershipType getById(int id) throws SQLException {
        String sql = "SELECT * FROM membership_types WHERE id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) return createFromResultSet(rs);
            }
        }
        return null;
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM membership_types WHERE id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        }
    }

    private MembershipType createFromResultSet(ResultSet rs) throws SQLException {
        return new MembershipType.Builder()
                .setId(rs.getInt("id"))
                .setName(rs.getString("name"))
                .setPrice(rs.getDouble("price"))
                .setDurationDays(rs.getInt("duration_days"))
                .build();
    }
}
