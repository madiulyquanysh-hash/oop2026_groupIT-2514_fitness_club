package entities;

import java.time.LocalDateTime;

public class Booking {
    private int id;
    private int memberId;
    private int classId;
    private String classTitle;
    private int classInstructorId;
    private LocalDateTime bookingDate;

    private Booking(Builder builder) {
        this.id = builder.id;
        this.memberId = builder.memberId;
        this.classId = builder.classId;
        this.classTitle = builder.classTitle;
        this.classInstructorId = builder.classInstructorId;
        this.bookingDate = builder.bookingDate;
    }

    public int getId() { return id; }
    public int getMemberId() { return memberId; }
    public int getClassId() { return classId; }
    public String getClassTitle() { return classTitle; }
    public int getClassInstructorId() { return classInstructorId; }
    public LocalDateTime getBookingDate() { return bookingDate; }

    public static class Builder {
        private int id;
        private int memberId;
        private int classId;
        private String classTitle;
        private int classInstructorId;
        private LocalDateTime bookingDate = LocalDateTime.now();

        public Builder setId(int id) { this.id = id; return this; }
        public Builder setMemberId(int memberId) { this.memberId = memberId; return this; }
        public Builder setClassId(int classId) { this.classId = classId; return this; }
        public Builder setClassTitle(String classTitle) { this.classTitle = classTitle; return this; }
        public Builder setClassInstructorId(int instructorId) { this.classInstructorId = instructorId; return this; }
        public Builder setBookingDate(LocalDateTime date) { this.bookingDate = date; return this; }

        public Booking build() {
            return new Booking(this);
        }
    }
}