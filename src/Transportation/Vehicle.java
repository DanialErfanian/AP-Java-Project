package Transportation;

import Logic.Game;
import Logic.MainObject;
import Products.Product;
import Utils.ProductPool;

abstract public class Vehicle extends MainObject {
    int level = 0, progress = 0;
    boolean onTheWay = false;
    ProductPool products;
    //TODO: add json constructor

    private int getProductCount(Product product) {
        return products.getProductCount(product);
    }

    public boolean addProduct(Product product, int count) {
        if (onTheWay)
            return false;
        return products.addProduct(product, count);
    }

    Vehicle(Game game) {
        super(game);
    }

    public int getCapacity() {
        return products.getCapacity();
    }

    public int getLevel() {
        return level;
    }

    public int getProgress() {
        return progress;
    }

    public boolean getOnTheWay() {
        return onTheWay;
    }

    abstract public void increaseTurn();


    abstract int getUpgradeCost();

    abstract int getUpgradeIncreaseCapacity();

    public boolean upgrade() {
        int cost = getUpgradeCost();
        if (!getGame().decreaseMoney(cost))
            return false;
        int increaseCapacity = getUpgradeIncreaseCapacity();
        products.increaseCapacity(increaseCapacity);
        return true;
    }

    public boolean clear() {
        if (onTheWay)
            return false;
        products.clear();
        return true;
    }

    abstract public boolean go();

    public String toString() {
        return this.getClass() + " :" +
                "\nlevel: " +
                level +
                "\nproducts = " +
                products;
    } // getInfo

    abstract public boolean canUpgrade();
}
