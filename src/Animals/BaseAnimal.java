package Animals;

import Logic.Game;
import Logic.MiddleMapObject;
import Logic.Targetable;
import Utils.Position;

import java.util.ArrayList;
import java.util.Random;

abstract public class BaseAnimal extends MiddleMapObject {
    Targetable target = null;

    BaseAnimal(Game game, Position position) {
        super(game, position);
    }


    abstract void regenerateTarget();

    protected boolean isTargetInvalid() {
        return target == null || target.getPosition() == null;
    }

    private void move() {
        if (isTargetInvalid()) regenerateTarget();
        if (isTargetInvalid())
            target = getGame().getMap().getRandomValidPosition();
        int[] dx = {-1, -0, +0, +1};
        int[] dy = {-0, -1, +1, +0};
        int distance = Position.getDistance(this.getPosition(), target.getPosition());
        if (distance == 0)
            return;
        ArrayList<Position> validPositions = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Position currentPosition = new Position(getPosition().getX() + dx[i], getPosition().getY() + dy[i]);
            if (Position.getDistance(currentPosition, target.getPosition()) == distance - 1)
                validPositions.add(currentPosition);
        }
        setPosition(validPositions.get(new Random().nextInt(validPositions.size())));
    }

    @Override
    public String toString() {
        return "BaseAnimal: " +
                "\nposition: " + getPosition() +
                "\ntarget: " + target.getPosition();
    }

    abstract void doTask();

    @Override
    public final void increaseTurn() {
        if (!this.isValid())
            return;
        doTask();
        if (!this.isValid())
            return;
        move();
    }
}
