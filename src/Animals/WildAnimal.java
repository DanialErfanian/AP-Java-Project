package Animals;

import Logic.Game;
import Logic.MiddleMapObject;
import Products.GroundProduct;
import Products.Product;
import Utils.Position;

import java.util.ArrayList;

public class WildAnimal extends BaseAnimal {
    private WildAnimalType type;

    @Override
    public String toString() {
        return "WildAnimal: \ntype: " + type + "\n" + super.toString();
    }

    @Override
    void doTask() {
        catchAnimal();
    }

    @Override
    public AnimalType getType() {
        if (type.equals(WildAnimalType.LION))
            return AnimalType.Lion;
        else if (type.equals(WildAnimalType.BEAR))
            return AnimalType.Bear;
        assert false;
        return null;
    }

    public WildAnimal(Game game, Position position) {
        super(game, position);
        this.type = Math.random() > 0.5 ? WildAnimalType.LION : WildAnimalType.BEAR;
    }

    @Override
    void regenerateTarget() {
        target = getGame().getMap().getRandomCatchableTarget();
    }

//    public WildAnimal(Game game, Position position, WildAnimalType type) {
//        super(game, position);
//        this.type = type;
//    }

    public GroundProduct cage() {
        Product product = this.type.equals(WildAnimalType.LION) ? Product.CagedLion : Product.CagedBrownBear;
        return new GroundProduct(getGame(), product, this.getPosition(), 1);
    }

    private void catchAnimal() {
        ArrayList<MiddleMapObject> cellObjects = this.getGame().getMap().getCellObjects(this.getPosition());
        for (int i = 0; i < cellObjects.size(); i++) {
            MiddleMapObject object = cellObjects.get(i);
            if (object instanceof WildAnimal)
                continue;
            cellObjects.set(i, null);
        }
    }
}
