package entities;

public class MembershipType {
    private int id;
    private String name;
    private double price;
    private int durationDays;

    public MembershipType(String name, double price, int durationDays) {
        this.name = name;
        this.price = price;
        this.durationDays = durationDays;
    }
// new
    public MembershipType(int id, String name, double price, int durationDays) {
        this(name, price, durationDays);
        this.id = id;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getDurationDays() { return durationDays; }
}