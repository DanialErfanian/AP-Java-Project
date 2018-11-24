package Animals;

import Utils.Position;

abstract public class BaseAnimal {
    private Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
