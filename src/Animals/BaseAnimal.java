package Animals;

import Logic.Game;
import Logic.MiddleMapObject;
import Logic.Targetable;
import Utils.Position;

abstract public class BaseAnimal extends MiddleMapObject {
    private AnimalState state = null;
    Targetable target = null;

    BaseAnimal(Game game, Position position) {
        super(game, position);
    }


    abstract void regenerateTarget();

    private boolean isTargetInvalid() {
        return (target == null) || (target.getPosition() == null) || target.getPosition().equals(getPosition());
    }

    private void move() {
        if (isTargetInvalid()) regenerateTarget();
        if (isTargetInvalid())
            target = getGame().getMap().getRandomValidPosition();
        int distance = Position.getDistance(this.getPosition(), target.getPosition());
        if (distance == 0) {
            move();
            return;
        }
        int[] dy = {-1, -0, +1, -1, -0, +1, -1, -0, +1};
        int[] dx = {-1, -1, -1, -0, -0, -0, +1, +1, +1};
        AnimalState[] animalStates = new AnimalState[dx.length];
        animalStates[0] = AnimalState.up_left;
        animalStates[1] = AnimalState.up;
        animalStates[2] = AnimalState.up_right;
        animalStates[3] = AnimalState.left;
        animalStates[4] = AnimalState.eat;
        animalStates[5] = AnimalState.right;
        animalStates[6] = AnimalState.down_left;
        animalStates[7] = AnimalState.down;
        animalStates[8] = AnimalState.down_right;
        int min = distance + 10;
        for (int i = 0; i < dx.length; i++) {
            Position currentPosition = new Position(getPosition().getX() + dx[i], getPosition().getY() + dy[i]);
            min = Math.min(min, Position.getDistance(currentPosition, target.getPosition()));
        }
        for (int i = 0; i < dx.length; i++) {
            Position currentPosition = new Position(getPosition().getX() + dx[i], getPosition().getY() + dy[i]);
            if (Position.getDistance(currentPosition, target.getPosition()) == min) {
                getGame().getMap().moveAnimal(this, currentPosition);
                this.state = animalStates[i];
                return;
            }
        }
    }

    @Override
    public String toString() {
        return "BaseAnimal: " +
                "\nposition: " + getPosition() +
                "\ntarget: " + ((target == null) ? "null" : target.getPosition());
    }

    abstract boolean doTask();

    @Override
    public final void increaseTurn() {
        if (!this.isValid())
            return;
        if (doTask()) {
            state = AnimalState.eat;
            return;
        }
        if (!this.isValid())
            return;
        move();
    }

    public AnimalState getState() {
        return state;
    }

    public abstract AnimalType getType();

    void deathState() {
        this.state = AnimalState.death;
    }
}
