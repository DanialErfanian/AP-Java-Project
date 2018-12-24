package Buildings;

import Logic.Constants;
import Logic.Game;

public class Well extends BaseBuilding {
    private int level = 0;
    private double capacity = Constants.WELL_INITIAL_CAPACITY, remainedWater = capacity;

    public Well(Game game) {
        super(game);
    }

    @Override
    public void increaseTurn() {
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

        return "Well: " +
                "\nlevel: " +
                level +
                "\ncapacity: " +
                capacity +
                "\nremainedWater: " +
                remainedWater;
    }

    public boolean fill() {
        int cost = Constants.WELL_INITIAL_REFILL_COST + level * Constants.WELL_UPGRADE_INCREASE_REFILL_COST;
        if (!getGame().decreaseMoney(cost))
            return false;
        remainedWater = capacity;
        return true;
    }

    public boolean upgrade() {
        int cost = Constants.WELL_UPGRADE_COST;
        if (!getGame().decreaseMoney(cost))
            return false;
        capacity += Constants.WELL_UPGRADE_INCREASE_CAPACITY;
        level++;
        return true;
    }
}
