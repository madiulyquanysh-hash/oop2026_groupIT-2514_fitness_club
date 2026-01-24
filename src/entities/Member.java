package entities;


public class Member {
    private int id;
    private String name;
    private String email;
    private int membershipTypeId;

    public Member(int id, String name, String email, int membershipTypeId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.membershipTypeId = membershipTypeId;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getMembershipTypeId() { return membershipTypeId; }
}