package Animals;

import Logic.Game;
import Utils.Position;

public class Dog extends BaseAnimal {
    private WildAnimal target;

    public Dog(Game game, Position position, WildAnimal target) {
        super(game, position);
        this.target = target;
    }

    public void startCatch() {

    }

    @Override
    public void increaseTurn() {

    }
}
