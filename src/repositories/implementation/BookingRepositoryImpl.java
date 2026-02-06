package repositories.implementation;

import edu.aitu.oop3.db.data.DatabaseConnection;
import entities.Booking;
import repositories.Repositories.BookingRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingRepositoryImpl implements BookingRepository {
    private final Connection connection;

    public BookingRepositoryImpl() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void add(Booking entity) throws SQLException {
        createBooking(entity.getMemberId(), entity.getClassId());
    }

    @Override
    public Booking getById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Booking> getAll() throws SQLException {
        String sql = "SELECT * FROM classes ORDER BY id ASC";
        return new ArrayList<>();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM class_bookings WHERE id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        }
    }

    @Override
    public void createBooking(int memberId, int classId) throws SQLException {
        connection.setAutoCommit(false);
        try {
            String insertSql = "INSERT INTO class_bookings (member_id, class_title, class_instructor_id) " +
                    "SELECT ?, title, id FROM classes WHERE id = ?";

            try (PreparedStatement ins = connection.prepareStatement(insertSql)) {
                ins.setInt(1, memberId);
                ins.setInt(2, classId);
                ins.executeUpdate();
            }

            String updateSql = "UPDATE classes SET remaining_capacity = remaining_capacity - 1 WHERE id = ? AND remaining_capacity > 0";
            try (PreparedStatement upd = connection.prepareStatement(updateSql)) {
                upd.setInt(1, classId);
                int rows = upd.executeUpdate();
                if (rows == 0) throw new SQLException("No capacity left!");
            }

            connection.commit();
            System.out.println("Capacity updated and booking saved!");
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }


    @Override
    public String addBooking(int memberId, int classId) throws SQLException {
        String classTitle = "";

        String checkSql = "SELECT title, remaining_capacity FROM classes WHERE id = ?";
        try (PreparedStatement checkStmt = connection.prepareStatement(checkSql)) {
            checkStmt.setInt(1, classId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                int remaining = rs.getInt("remaining_capacity");
                if (remaining <= 0) {
                    throw new SQLException("No slots available for this class!");
                }
                classTitle = rs.getString("title");
            } else {
                throw new SQLException("Class not found!");
            }
        }

        String updateSql = "UPDATE classes SET remaining_capacity = remaining_capacity - 1 WHERE id = ?";
        try (PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
            updateStmt.setInt(1, classId);
            updateStmt.executeUpdate();
        }

        String insertSql = "INSERT INTO class_bookings (member_id, class_instructor_id, class_title) VALUES (?, ?, ?)";
        try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
            insertStmt.setInt(1, memberId);
            insertStmt.setInt(2, classId);
            insertStmt.setString(3, classTitle);
            insertStmt.executeUpdate();
        }
        return classTitle;
    }
}