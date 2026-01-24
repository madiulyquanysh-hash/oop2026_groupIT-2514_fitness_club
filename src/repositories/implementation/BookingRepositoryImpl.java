package repositories.implementation;

import edu.aitu.oop3.db.data.DatabaseConnection;
import entities.Booking;
import repositories.interfaces.BookingRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingRepositoryImpl implements BookingRepository {

    @Override
    public void add(Booking booking) {
        String sql = "INSERT INTO bookings(member_id, class_id, booking_date) VALUES (?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, booking.getMemberId());
            ps.setInt(2, booking.getClassId());
            ps.setTimestamp(3, Timestamp.valueOf(booking.getBookingDate()));
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Booking> findAll() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings";

        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                bookings.add(new Booking(
                        rs.getInt("id"),
                        rs.getInt("member_id"),
                        rs.getInt("class_id"),
                        rs.getTimestamp("booking_date").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
}
