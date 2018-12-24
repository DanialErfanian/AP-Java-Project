package Transportation;

import Logic.Game;
import Products.Product;

public class Truck extends Vehicle {

    public Truck(Game game) {
        super(game);
    }

    @Override
    public boolean upgrade() {
        return false;
    }

    public String toString() {
        return null;
    }

    @Override
    public void increaseTurn() {

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
}
