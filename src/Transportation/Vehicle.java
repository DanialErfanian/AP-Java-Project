package Transportation;

import Logic.Game;
import Logic.MainObject;
import Products.Product;

abstract public class Vehicle extends MainObject {
    private int capacity, level, progress;
    private boolean onTheWay;

    Vehicle(Game game) {
        super(game);
    }

    public int getCapacity() {
        return capacity;
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

    public abstract boolean upgrade();

    abstract public String toString();

    abstract public void increaseTurn();

    abstract public boolean add(Product product, int count);

    abstract public void clear();

    abstract public boolean go();
}
