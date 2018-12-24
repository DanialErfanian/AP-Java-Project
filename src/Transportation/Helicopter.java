package Transportation;

import Logic.Constants;
import Logic.Game;
import Products.Product;
import Utils.ProductPool;

import java.util.HashMap;

public class Helicopter extends Vehicle {

    public Helicopter(Game game) {
        super(game);
        capacity = Constants.HELICOPTER_INITIAL_CAPACITY;
        products = new ProductPool(capacity);
        remainedCapacity = capacity;
    }

    int getUpgradeCost() {
        return Constants.HELICOPTER_UPGRADE_COST;
    }

    int getUpgradeIncreaseCapacity() {
        return Constants.HELICOPTER_UPGRADE_INCREASE_CAPACITY;
    }


    private int getProfit() {
        int sum = 0;
        for (HashMap.Entry<Product, Integer> entry : products.getEnrtySet()) {
            Product product = entry.getKey();
            int count = entry.getValue();
            sum += product.getSellProfit() * count;
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
        progress = Constants.HELICOPTER_JOB_PROGRESS;
        return true;
    }

    @Override
    public boolean canUpgrade() {
        int cost = getUpgradeCost();
        return getGame().getMoney() >= cost;
    }
}
