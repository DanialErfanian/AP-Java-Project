package Buildings;

import Logic.Game;

public class Well extends BaseBuilding {
    private double capacity, remainedWater;

    public Well(Game game) {
        super(game);
    }

    @Override
    protected void increaseTurn() {
        // Empty
    }

    public double getRemainedWater() {
        return remainedWater;
    }

    public void setRemainedWater(double remainedWater) {
        this.remainedWater = remainedWater;
    }

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
