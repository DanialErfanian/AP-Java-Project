package Animals;

import Logic.Game;
import Utils.Position;

abstract class WildAnimal extends BaseAnimal {// TODO increaseTurn must run catch if it's needed
    private boolean catched = false;
    private int catchedTime = 0;

    WildAnimal(Game game, Position position) {
        super(game, position);
    }


    private void escape() {

    }

    private void catchAnimal() {
    }
}
