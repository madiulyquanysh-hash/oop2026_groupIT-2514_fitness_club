package entities;

public class FitnessClass {
    private int id;
    private String title;
    private String instructor;
    private double cost;
    private int capacity;
    private int remainingCapacity;

    public FitnessClass(int id, String title, String instructor, double cost, int capacity, int remainingCapacity) {
        this.id = id;
        this.title = title;
        this.instructor = instructor;
        this.cost = cost;
        this.capacity = capacity;
        this.remainingCapacity = remainingCapacity;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getInstructor() { return instructor; }
    public double getCost() { return cost; }
    public int getCapacity() { return capacity; }
    public int getRemainingCapacity() { return remainingCapacity; }
}