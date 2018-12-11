package Animals;

import Logic.Game;

public class Dog extends BaseAnimal {
    private WildAnimal target;

    public Dog(Game game, WildAnimal target) {
        super(game);
        this.target = target;
    }

    public void startCatch() {

    }

    @Override
    public void increaseTurn() {

    }
}
