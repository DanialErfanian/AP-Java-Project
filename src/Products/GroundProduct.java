package Products;

import Logic.Game;
import Logic.MiddleMapObject;
import Utils.Position;

public class GroundProduct extends MiddleMapObject {
    private Product type;
    private Position position;
    private int amount = 1;
    private int constructTime = 5;

    public GroundProduct(Game game, Product type, Position position, int amount) {
        super(game);
        this.type = type;
        this.position = position;
        this.amount = amount;
    }

    @Override
    protected void increaseTurn() {

    }

    public void collect() {

    }

}
