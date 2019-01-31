package Animals;

import Logic.Game;
import Utils.Position;

public class Cat extends BaseAnimal {

    public Cat(Game game, Position position) {
        super(game, position);
    }

    @Override
    void regenerateTarget() {
        target = getGame().getMap().getGroundProductForCat(getPosition());
    }

    @Override
    void doTask() {
        getGame().collect(getPosition());
    }

    @Override
    public AnimalType getType() {
        return AnimalType.Cat;
    }

    @Override
    public String toString() {
        return "Cat: \n" + super.toString();
    }
}
