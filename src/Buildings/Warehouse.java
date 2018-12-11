package Buildings;

import Logic.Game;
import Products.Product;

import java.util.HashMap;

public class Warehouse extends BaseBuilding {
    public int getCapacity() {
        return capacity;
    }

    private int capacity;
    //TODO: add json constructor
    HashMap<Product, Integer> prodcuts = new HashMap<>();

    public Warehouse(Game game) {
        super(game);
    }

    @Override
    protected void increaseTurn() {// Empty :)
    }

    public String toString() {
        return null;
    }

    public boolean upgrade() {
        return false;
    }
}
