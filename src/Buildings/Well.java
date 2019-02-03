package Buildings;

import Logic.Constants;
import Logic.Game;

public class Well extends BaseBuilding {
    private int level = 1;
    private double capacity = Constants.WELL_INITIAL_CAPACITY, remainedWater = capacity;

    public Well(Game game) {
        super(game);
    }

    @Override
    public void increaseTurn() {
        // Empty
    }

    public boolean plant() {
        double cost = Constants.NEW_PLANT_NEEDED_WATER;
        if (remainedWater < cost)
            return false;
        remainedWater -= cost;
        return true;
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
        if (!haveUpgrade())
            return false;
        int cost = getUpgradeCost();
        if (!getGame().decreaseMoney(cost))
            return false;
        capacity += Constants.WELL_UPGRADE_INCREASE_CAPACITY;
        level++;
        return true;
    }

    @Override
    public int getUpgradeCost() {
        return Constants.WELL_UPGRADE_COST;
    }

    public boolean haveUpgrade() {
        return level != Constants.WELL_MAX_LEVEL;
    }

    public int getLevel() {
        return this.level;
    }
}
