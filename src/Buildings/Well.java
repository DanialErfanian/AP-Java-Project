package Buildings;

public class Well extends BaseBuilding {
    private double capacity;

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getCapacity() {
        return capacity;
    }

    public String toString() {
        return null;
    }

    public void fill() {
    }

    public boolean upgrade() {
        return false;
    }
}
