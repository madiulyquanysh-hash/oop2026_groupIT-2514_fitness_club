package entities;


public class FitnessClass {
    private int id;
    private String title;
    private String instructor;
    private int capacity;

    public FitnessClass(int id, String title, String instructor, int capacity) {
        this.id = id;
        this.title = title;
        this.instructor = instructor;
        this.capacity = capacity;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public int getCapacity() { return capacity; }
}
