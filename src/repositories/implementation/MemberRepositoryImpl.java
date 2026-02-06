package repositories.implementation;

import edu.aitu.oop3.db.data.DatabaseConnection;
import entities.Member;
import repositories.Repositories.MemberRepository;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberRepositoryImpl implements MemberRepository {
    private final Connection connection;

    public MemberRepositoryImpl() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public Member findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM members WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Member.Builder()
                        .setId(rs.getInt("id"))
                        .setName(rs.getString("full_name")) // исправлено
                        .setEmail(rs.getString("email"))
                        .setMembershipTypeId(rs.getInt("membership_type_id")) // добавлено
                        .setExpiryDate(rs.getDate("expiry_date"))
                        .build();
            }
        }
        return null;
    }


    @Override
    public void add(Member member) throws SQLException {
        String sql = "INSERT INTO members (full_name, email, membership_type_id, expiry_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getEmail());
            stmt.setInt(3, member.getMembershipTypeId());
            stmt.setDate(4, member.getExpiryDate());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Member> getAll() throws SQLException {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM members";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                members.add(createMemberFromResultSet(rs));
            }
        }
        return members;
    }

    @Override
    public Member getById(int id) throws SQLException {
        String sql = "SELECT * FROM members WHERE id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) return createMemberFromResultSet(rs);
            }
        }
        return null;
    }

    @Override
    public Member getMemberByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM members WHERE email = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, email);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) return createMemberFromResultSet(rs);
            }
        }
        return null;
    }

    @Override
    public List<Member> getActiveMembers() throws SQLException {
        List<Member> allMembers = getAll();
        LocalDate today = LocalDate.now();
        return allMembers.stream()
                .filter(m -> m.getExpiryDate() != null && m.getExpiryDate().toLocalDate().isAfter(today))
                .collect(Collectors.toList());
    }

    @Override
    public List<Member> searchByName(String name) throws SQLException {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM members WHERE full_name ILIKE ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, "%" + name + "%");
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    members.add(createMemberFromResultSet(rs));
                }
            }
        }
        return members;
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM members WHERE id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        }
    }

    private Member createMemberFromResultSet(ResultSet rs) throws SQLException {
        return new Member.Builder()
                .setId(rs.getInt("id"))
                .setName(rs.getString("full_name"))
                .setEmail(rs.getString("email"))
                .setMembershipTypeId(rs.getInt("membership_type_id"))
                .setExpiryDate(rs.getDate("expiry_date"))
                .build();
    }
}