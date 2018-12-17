package Products;

import Logic.Game;
import Logic.MiddleMapObject;
import Utils.Position;

public class GroundProduct extends MiddleMapObject {
    Product type;
    Position position;
    int amount = 1;
    private int constructTime = 5;

    public GroundProduct(Game game) {
        super(game);
    }

    @Override
    protected void increaseTurn() {

    }

    public void collect() {

    }

}
