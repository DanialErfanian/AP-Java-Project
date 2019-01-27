package Transportation;

import Logic.Constants;
import Logic.Game;
import Products.Product;
import Products.ProductPool;

import java.util.HashMap;

public class Helicopter extends Vehicle {

    public Helicopter(Game game) {
        super(game);
        int capacity = Constants.HELICOPTER_INITIAL_CAPACITY;
        products = new ProductPool(capacity);
    }

    int getUpgradeCost() {
        return Constants.HELICOPTER_UPGRADE_COST;
    }

    int getUpgradeIncreaseCapacity() {
        return Constants.HELICOPTER_UPGRADE_INCREASE_CAPACITY;
    }


    private int getCost() {
        int sum = 0;
        for (HashMap.Entry<Product, Integer> entry : products.getEntrySet()) {
            Product product = entry.getKey();
            int count = entry.getValue();
            sum += product.getBuyCost() * count;
        }
        return sum;
    }

    @Override
    public void increaseTurn() {
        if (progress == 0) {
            onTheWay = false;
            assert (getGame().getWarehouse().unallocate(products.getFilledCapacity()));
            for (HashMap.Entry<Product, Integer> entry : products.getEntrySet()) {
                assert (getGame().getWarehouse().addProduct(entry.getKey(), entry.getValue()));
            }
            clear();
        } else
            progress--;
    }

    private boolean canGo() {
        if (getGame().getMoney() < getCost())
            return false;
        return getGame().getWarehouse().getRemainedCapacity() >= products.getFilledCapacity();
    }

    @Override
    public boolean go() {
        if (onTheWay)
            return false;
        if (!canGo())
            return false;
        getGame().decreaseMoney(getCost());
        assert (getGame().getWarehouse().allocate(products.getFilledCapacity()));
        onTheWay = true;
        progress = Constants.HELICOPTER_JOB_PROGRESS;
        return true;
    }
}
