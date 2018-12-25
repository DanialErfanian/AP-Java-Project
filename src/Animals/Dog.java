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
    void doTask() {
        catchWildAnimal();
    }

    private void catchWildAnimal() {
        ArrayList<MiddleMapObject> cellObjects = this.getGame().getMap().getCellObjects(this.getPosition());
        for (MiddleMapObject object : cellObjects)
            if (object instanceof WildAnimal) {
                ((WildAnimal) object).die();
                this.die();
                return;
            }
    }

    @Override
    public String toString() {
        return "Gog: \n" + super.toString();
    }
}
