package Logic;

import Utils.Position;

abstract public class MiddleMapObject extends MainObject implements Targetable {
    protected Position position; // TODO only getter is ok?

    public MiddleMapObject(Game game, Position position) {
        super(game);
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    protected final boolean isValid(){
        return position != null;
    }

}
