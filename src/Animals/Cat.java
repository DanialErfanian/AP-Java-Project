package Animals;

import Logic.Game;
import Utils.Position;

public class Cat extends BaseAnimal {

    public Cat(Game game, Position position) {
        super(game, position);
    }

    public boolean upgrade() {
        return false;
    }

    @Override
    public void increaseTurn() {

    }
}
