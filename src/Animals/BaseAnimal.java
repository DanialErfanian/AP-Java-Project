package Animals;

import Logic.Game;
import Logic.MiddleMapObject;
import Utils.Position;

abstract public class BaseAnimal extends MiddleMapObject {
    private Position position;

    BaseAnimal(Game game, Position position) {
        super(game);
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
