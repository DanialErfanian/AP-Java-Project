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

    public void go() {
    }

    @Override
    void upgrade() {

    }

    @Override
    public void done() {

    }

    public String toString() {
        return null;
    }

    @Override
    public void increaseTurn() {

    }
}
