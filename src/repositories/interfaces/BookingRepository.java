package repositories.interfaces;

import entities.Booking;
import java.util.List;

public interface BookingRepository {
    void add(Booking booking);
    List<Booking> findAll();
}

