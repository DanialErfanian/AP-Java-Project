package Animals;

import Logic.Game;
import Logic.MiddleMapObject;
import Products.GroundProduct;
import Products.Product;
import Utils.Position;

import java.util.ArrayList;

public class WildAnimal extends BaseAnimal {// TODO increaseTurn must run catch if it's needed
    private WildAnimalType type;

    public WildAnimalType getType() {
        return type;
    }

    public WildAnimal(Game game, Position position) {
        super(game, position);
        this.type = Math.random() > 0.5 ? WildAnimalType.LION : WildAnimalType.BEAR;
    }

    public WildAnimal(Game game, Position position, WildAnimalType type) {
        super(game, position);
        this.type = type;
    }

    public GroundProduct cage() {
        return new GroundProduct(getGame(),
                (this.type.equals(WildAnimalType.LION) ? Product.LION : Product.BEAR),
                this.getPosition(),
                1
        );
    }

    private void catchAnimal() {
        ArrayList<MiddleMapObject> cellObjects = this.getGame().getMap().getCellObjects(this.getPosition());
        for (int i = 0; i < cellObjects.size(); i++) {
            MiddleMapObject object = cellObjects.get(i);
            if (object instanceof WildAnimal)
                continue;
            if (object instanceof GroundProduct && ((GroundProduct) object).getAmount() == 0)
                continue;
            cellObjects.set(i, null);
        }
    }

    @Override
    protected void increaseTurn() {
        this.setPosition(this.getGame().getMap().getRandomValidAdjacentPosition(this.getPosition()));
    }
}
