package Transportation;

import Logic.Game;

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
    public void done() {

    }
}
