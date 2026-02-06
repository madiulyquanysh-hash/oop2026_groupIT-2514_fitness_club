package repositories.Repositories;

import entities.Booking;
import java.sql.SQLException;

public interface BookingRepository extends CrudRepository<Booking> {
    void createBooking(int memberId, int classId) throws SQLException;
    String addBooking(int memberId, int classId) throws SQLException;
}