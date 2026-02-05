package entities;

import java.sql.Date;

public class Member {
    private int id;
    private String name;
    private String email;
    private int membershipTypeId;
    private String instructor;
    private Date expiryDate;

    public Member(int id, String name, String email, int membershipTypeId, String instructor, Date expiryDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.membershipTypeId = membershipTypeId;
        this.instructor = instructor;
        this.expiryDate = expiryDate;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getMembershipTypeId() { return membershipTypeId; }
    public String getInstructor() { return instructor; }
    public Date getExpiryDate() { return expiryDate; }
}