package entities;

import java.time.LocalDateTime;

public class Booking {
    private int id;
    private int memberId;
    private int classId;
    private LocalDateTime bookingDate;

    public Booking(int id, int memberId, int classId, LocalDateTime bookingDate) {
        this.id = id;
        this.memberId = memberId;
        this.classId = classId;
        this.bookingDate = bookingDate;
    }

    public int getMemberId() { return memberId; }
    public int getClassId() { return classId; }
    public LocalDateTime getBookingDate() { return bookingDate; }
}
