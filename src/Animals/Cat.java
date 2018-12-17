package Animals;

import Logic.Game;

public class Cat extends BaseAnimal {
    public Cat(Game game) {
        super(game);
    }

    public boolean upgrade() {
        return false;
    }

    @Override
    public void increaseTurn() {

    }
}
