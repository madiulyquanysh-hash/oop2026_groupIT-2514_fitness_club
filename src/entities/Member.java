package entities;

import java.sql.Date;

public class Member {
    private int id;
    private String name;
    private String email;
    private int membershipTypeId;
    private Date expiryDate;

    private Member(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.membershipTypeId = builder.membershipTypeId;
        this.expiryDate = builder.expiryDate;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getMembershipTypeId() { return membershipTypeId; }
    public Date getExpiryDate() { return expiryDate; }

    public static class Builder {
        private int id;
        private String name;
        private String email;
        private int membershipTypeId;
        private Date expiryDate;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setMembershipTypeId(int typeId) {
            this.membershipTypeId = typeId;
            return this;
        }

        public Builder setExpiryDate(Date expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

        public Member build() {
            return new Member(this);
        }
    }

    public boolean isActive() {
        if (this.expiryDate == null) return false;
        return !this.expiryDate.toLocalDate().isBefore(java.time.LocalDate.now());
    }

    @Override
    public String toString() {
        return String.format("ID: %-3s | Name: %-15s | Email: %-25s | Membership type: %d| Expiry date: %s | Status: %s",
                this.id,
                this.name,
                this.email,
                this.membershipTypeId,
                this.expiryDate,
                isActive() ? "ACTIVE" : "!!!   EXPIRED   !!!" );
    }
}