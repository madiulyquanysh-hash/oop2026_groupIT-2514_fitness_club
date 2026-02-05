package repositories.implementation;

import entities.MembershipType;
import repositories.Repositories.MembershipTypeRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembershipTypeRepositoryImpl implements MembershipTypeRepository {
    private final Connection connection;

    public MembershipTypeRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<MembershipType> getAllTypes() throws SQLException {
        List<MembershipType> types = new ArrayList<>();
        String sql = "SELECT * FROM membership_types";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                types.add(new MembershipType(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("duration_days")
                ));
            }
        }
        return types;
    }
}