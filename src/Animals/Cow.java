package Animals;

import Logic.Game;
import Products.Product;
import Utils.Position;

public class Cow extends ProducerAnimal {
    Cow(Game game, Position position) {
        super(game, position);
    }

    @Override
    Product getProduct() {
        return null;
    }

    @Override
    public void increaseTurn() {

    }
}
