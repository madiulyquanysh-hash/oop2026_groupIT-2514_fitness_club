package entities;

public class MembershipType {
    private int id;
    private String name;
    private double price;
    private int durationDays;

    private MembershipType(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.price = builder.price;
        this.durationDays = builder.durationDays;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getDurationDays() { return durationDays; }

    public static class Builder {
        private int id;
        private String name;
        private double price;
        private int durationDays;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setDurationDays(int durationDays) {
            this.durationDays = durationDays;
            return this;
        }

        public MembershipType build() {
            return new MembershipType(this);
        }
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Type: %-10s | Price: %-8s | Duration mouthes: %d",
            this.id,
            this.name,
            this.price,
            this.durationDays);
    }
}
