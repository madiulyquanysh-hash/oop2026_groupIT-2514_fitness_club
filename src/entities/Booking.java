package entities;

import java.time.LocalDateTime;

public class Booking {
    private int memberId;
    private String classTitle;
    private int classInstructorId;
    private LocalDateTime bookingDate;
// new
    public Booking(int memberId, String classTitle, int classInstructorId, LocalDateTime bookingDate) {
        this.memberId = memberId;
        this.classTitle = classTitle;
        this.classInstructorId = classInstructorId;
        this.bookingDate = bookingDate;
    }

    public int getMemberId() { return memberId; }
    public String getClassTitle() { return classTitle; }
    public int getClassInstructorId() { return classInstructorId; }
    public LocalDateTime getBookingDate() { return bookingDate; }
}