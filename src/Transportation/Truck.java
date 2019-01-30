package Transportation;

import Logic.Constants;
import Logic.Game;
import Products.Product;
import Products.ProductPool;

import java.util.HashMap;

public class Truck extends Vehicle {

    public Truck(Game game) {
        super(game);
        int capacity = Constants.TRUCK_INITIAL_CAPACITY;
        products = new ProductPool(capacity);
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
        for (HashMap.Entry<Product, Integer> entry : products.getEntrySet()) {
            Product product = entry.getKey();
            int count = entry.getValue();
            sum += product.getSaleCost() * count;
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
        progress = getFullProgress();
        return true;
    }

    @Override
    public int getFullProgress() {
        return Constants.TRUCK_JOB_PROGRESS;
    }
}
