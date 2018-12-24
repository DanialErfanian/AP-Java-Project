package Buildings;

import Logic.Constants;
import Logic.Game;
import Products.Product;
import Utils.ProductPool;

public class Warehouse extends BaseBuilding {
    private int level = 1;
    private ProductPool products = new ProductPool(Constants.WAREHOUSE_INITIAL_CAPACITY);
    //TODO: add json constructor

    public Warehouse(Game game) {
        super(game);
    }

    int getProductCount(Product product) {
        return products.getProductCount(product);
    }

    public boolean addProduct(Product product, int count) {
        return products.addProduct(product, count);
    }

    public int getCapacity() {
        return products.getCapacity();
    }

    public int getRemainedCapacity() {
        return products.getRemainedCapacity();
    }

    @Override
    protected void increaseTurn() {// Empty :)
    }

    public String toString() {
        return "Warehouse :" +
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
        products.increaseCapacity(increaseCapacity);
        level++;
        return true;
    }

    public int getLevel() {
        return level;
    }
}
