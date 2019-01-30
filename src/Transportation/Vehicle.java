package Transportation;

import Logic.Constants;
import Logic.Game;
import Logic.MainObject;
import Products.Product;
import Products.ProductPool;

abstract public class Vehicle extends MainObject {
    private int level = 1;
    int progress = 0;
    boolean onTheWay = false;
    ProductPool products;

    public boolean addProduct(Product product, int count) {
        if (onTheWay)
            return false;
        return products.addProduct(product, count);
    }

    Vehicle(Game game) {
        super(game);
    }

    abstract public void increaseTurn();


    abstract int getUpgradeCost();

    abstract int getUpgradeIncreaseCapacity();

    public final boolean upgrade() {
        if (!canUpgrade())
            return false;
        int cost = getUpgradeCost();
        getGame().decreaseMoney(cost);
        level++;
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

    public final String toString() {
        return this.getClass() + " :" +
                "\nlevel: " +
                level +
                "\nonTheWay: " +
                onTheWay +
                "\nprogress: " +
                progress +
                "\nproducts = " +
                products;
    } // getInfo

    private boolean canUpgrade() {
        if(level >= Constants.VEHICLE_MAX_LEVEL)
            return false;
        return getGame().getMoney() >= getUpgradeCost();
    }

    public abstract int getFullProgress();

    public int getLevel() {
        return level;
    }

    public int getProgress() {
        return progress;
    }
}
