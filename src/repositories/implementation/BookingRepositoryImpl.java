package repositories.implementation;

import repositories.Repositories.BookingRepository;
import java.sql.*;

public class BookingRepositoryImpl implements BookingRepository {
    private final Connection connection;

    public BookingRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createBooking(int memberId, int classId) throws SQLException {
        connection.setAutoCommit(false);
        try {
            String insertSql = "INSERT INTO class_bookings (member_id, class_title, class_instructor_id) SELECT ?, title, id FROM classes WHERE id = ?";

            try (PreparedStatement ins = connection.prepareStatement(insertSql)) {
                ins.setInt(1, memberId);
                ins.setInt(2, classId);
                ins.executeUpdate();
            }

            String updateSql = "UPDATE classes SET remaining_capacity = remaining_capacity - 1 WHERE id = ?";
            try (PreparedStatement upd = connection.prepareStatement(updateSql)) {
                upd.setInt(1, classId);
                upd.executeUpdate();
            }
// connection
            connection.commit();
            System.out.println("Capacity updated and booking saved!");
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}