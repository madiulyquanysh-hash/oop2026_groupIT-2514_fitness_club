package repositories.Repositories;

import java.sql.SQLException;

public interface BookingRepository {
    void createBooking(int memberId, int classId) throws SQLException;
}