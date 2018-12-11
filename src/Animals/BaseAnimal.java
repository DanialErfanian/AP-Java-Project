package Animals;

import Logic.Game;
import Logic.MiddleMapObject;
import Utils.Position;

abstract public class BaseAnimal extends MiddleMapObject {
    private Position position;

    public BaseAnimal(Game game) {
        super(game);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
