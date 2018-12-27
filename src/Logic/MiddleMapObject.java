package Logic;

import Utils.Position;

abstract public class MiddleMapObject extends MainObject implements Targetable {
    private Position position;

    public MiddleMapObject(Game game, Position position) {
        super(game);
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public final void destruct() {
        position = null;
    }

    protected final boolean isValid() {
        return position != null;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
