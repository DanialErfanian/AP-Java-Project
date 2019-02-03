package Animals;

import Logic.Game;
import Logic.MiddleMapObject;
import Utils.Position;

import java.util.ArrayList;

public class Dog extends BaseAnimal {
    public Dog(Game game, Position position) {
        super(game, position);
    }

    @Override
    void regenerateTarget() {
        target = getGame().getMap().getRandomWildAnimal();
    }

    @Override
    boolean doTask() {
        catchWildAnimal();
        return false;
    }

    @Override
    public AnimalType getType() {
        return AnimalType.Dog;
    }

    private void catchWildAnimal() {
        ArrayList<MiddleMapObject> cellObjects = this.getGame().getMap().getCellObjects(this.getPosition());
        for (MiddleMapObject object : cellObjects)
            if (object instanceof WildAnimal) {
                object.destruct();
                this.destruct();
                return;
            }
    }

    @Override
    public String toString() {
        return "Gog: \n" + super.toString();
    }
}
