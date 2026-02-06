package entities;

public class FitnessClass {
    private int id;
    private String title;
    private String instructor;
    private double cost;
    private int capacity;
    private int currentReservations;
    private int remainingCapacity;

    private FitnessClass(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.instructor = builder.instructor;
        this.cost = builder.cost;
        this.capacity = builder.capacity;
        this.remainingCapacity = builder.remainingCapacity;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getInstructor() {
        return instructor;
    }

    public double getCost() {
        return cost;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRemainingCapacity() {
        return remainingCapacity;
    }

    public static class Builder {
        private int id;
        private String title;
        private String instructor;
        private double cost;
        private int capacity;
        private int remainingCapacity;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setInstructor(String instructor) {
            this.instructor = instructor;
            return this;
        }

        public Builder setCost(double cost) {
            this.cost = cost;
            return this;
        }

        public Builder setCapacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public Builder setRemainingCapacity(int remainingCapacity) {
            this.remainingCapacity = remainingCapacity;
            return this;
        }

        public FitnessClass build() {
            return new FitnessClass(this);
        }
    }

    @Override
    public String toString() {
        String slotsInfo = String.format("%d/%d", this.remainingCapacity, this.capacity);
        return String.format("ID: %-2d | Title: %-10s | Instructor: %-20s | Cost: %-8.0f | Slots: %s",
                this.id,
                this.title,
                this.instructor,
                this.cost,
                slotsInfo);
    }
}