package Buildings;

import Logic.Constants;
import Logic.Game;
import Products.Product;

import java.util.HashMap;

public class Warehouse extends BaseBuilding {
    private int capacity = Constants.WAREHOUSE_INITIAL_CAPACITY;
    private int remainedCapacity = capacity;
    private int level = 1;
    private HashMap<Product, Integer> products = new HashMap<>();
    //TODO: add json constructor


    int getProductCount(Product product) {
        return products.getOrDefault(product, 0);
    }

    public boolean addProduct(Product product, int count) {
        if (remainedCapacity < count) {
            return false;
        }
        int current = this.getProductCount(product);
        if (current < -count)
            return false;
        products.put(product, current + count);
        remainedCapacity -= count;
        return true;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRemainedCapacity() {
        return remainedCapacity;
    }

    public Warehouse(Game game) {
        super(game);
    }

    @Override
    protected void increaseTurn() {// Empty :)
    }

    public String toString() {
        return "Warehouse :" +
                "\ncapacity = " +
                capacity +
                "\nremainedCapacity = " +
                remainedCapacity +
                "\nlevel: " +
                level +
                "\nproducts = " +
                products;

    } // getInfo

    public boolean upgrade() {
        int cost = Constants.WAREHOUSE_UPGRADE_COST;
        if (!getGame().decreaseMoney(cost))
            return false;
        int increaseCapacity = Constants.WAREHOUSE_UPGRADE_INCREASE_CAPACITY;
        capacity += increaseCapacity;
        remainedCapacity += increaseCapacity;
        level++;
        return true;
    }

    public int getLevel() {
        return level;
    }
}
