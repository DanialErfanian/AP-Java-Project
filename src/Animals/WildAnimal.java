package Animals;

import Logic.Game;
import Utils.Position;

abstract public class WildAnimal extends BaseAnimal {// TODO increaseTurn must run catch if it's needed
    private boolean catched = false;
    private int catchedTime = 0;

    WildAnimal(Game game, Position position) {
        super(game, position);
    }

    public void cage(){
        catched = true;
    }

    private void escape() {

    }

    private void catchAnimal() {
    }
}
