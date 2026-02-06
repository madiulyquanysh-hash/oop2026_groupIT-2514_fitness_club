package repositories.implementation;

import entities.Member;
import repositories.Repositories.MemberRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberRepositoryImpl implements MemberRepository {
    private final Connection connection;
    public MemberRepositoryImpl(Connection connection) {
        this.connection = connection;
    }
// new
    @Override
    public void addMember(Member member) throws SQLException {
        String sql = "INSERT INTO members (full_name, email, membership_type_id, expiry_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, member.getName());
            st.setString(2, member.getEmail());
            st.setInt(3, member.getMembershipTypeId());
            st.setDate(4, member.getExpiryDate());
            st.executeUpdate();
        }
    }

    @Override
    public Member getMemberByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM members WHERE email = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Member(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getInt("membership_type_id"),
                        rs.getString("instructor"),
                        rs.getDate("expiry_date")
                );
            }
        }
        return null;
    }

    @Override
    public List<Member> searchByName(String name) throws SQLException {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM members WHERE full_name ILIKE ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                members.add(new Member(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getInt("membership_type_id"),
                        rs.getString("instructor"),
                        rs.getDate("expiry_date")
                ));
            }
        }
        return members;
    }

    @Override
    public List<Member> getAllMembers() throws SQLException {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM members";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                members.add(new Member(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getInt("membership_type_id"),
                        rs.getString("instructor"),
                        rs.getDate("expiry_date")
                ));
            }
        }
        return members;
    }
}
