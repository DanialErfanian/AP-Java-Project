package Transportation;

import Logic.Game;
import Products.Product;

import java.util.HashMap;

public class Helicopter extends Vehicle {
    private HashMap<Product, Integer> products = new HashMap<>();
    private int cost = 0;

    public Helicopter(Game game) {
        super(game);
    }

    public int getCost() {
        return cost;
    }

    public void addProduct(Product product, int count) {
        cost += 1;
        // check kone onTheWay nabashe
        // ad mikone to products
        // va cost ro update mikone ke anna ho:)
    }

    @Override
    public boolean upgrade() {
        return false;
    }

    @Override
    public void done() {

    }

    @Override
    public boolean add(Product product, int count) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean go() {
        return false;
    }

    public String toString() {
        return null;
    }

    @Override
    public void increaseTurn() {

    }
}
