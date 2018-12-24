package Transportation;

import Logic.Constants;
import Logic.Game;
import Products.Product;
import Utils.ProductPool;

import java.util.HashMap;

public class Truck extends Vehicle {

    public Truck(Game game) {
        super(game);
        capacity = Constants.TRUCK_INITIAL_CAPACITY;
        products = new ProductPool(capacity);
        remainedCapacity = capacity;
    }

    int getUpgradeCost() {
        return Constants.TRUCK_UPGRADE_COST;
    }

    @Override
    int getUpgradeIncreaseCapacity() {
        return Constants.TRUCK_UPGRADE_INCREASE_CAPACITY;
    }

    private int getProfit() {
        int sum = 0;
        for (HashMap.Entry<Product, Integer> entry : products.getEnrtySet()) {
            Product product = entry.getKey();
            int count = entry.getValue();
            sum += product.getBuyProfit() * count;
        }
        return sum;
    }

    @Override
    public void increaseTurn() {
        if (progress == 0) {
            getGame().increaseMoney(getProfit());
            onTheWay = false;
            clear();
        } else
            progress--;
    }

    @Override
    public boolean go() {
        if (onTheWay)
            return false;
        onTheWay = true;
        progress = Constants.TRUCK_JOB_PROGRESS;
        return true;
    }

    @Override
    public boolean canUpgrade() {
        int cost = getUpgradeCost();
        return getGame().getMoney() >= cost;
    }
}
