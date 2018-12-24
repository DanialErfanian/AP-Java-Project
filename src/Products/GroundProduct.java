package Products;

import Buildings.Warehouse;
import Logic.Constants;
import Logic.Game;
import Logic.MiddleMapObject;
import Utils.Position;

public class GroundProduct extends MiddleMapObject {
    private Product type;
    private Position position;
    private int amount;
    private int constructTime = Constants.GROUND_PRODUCT_TIMEOUT;

    public GroundProduct(Game game, Product type, Position position, int amount) {
        super(game);
        this.type = type;
        this.position = position;
        this.amount = amount;
    }

    @Override
    protected void increaseTurn() {
        if (constructTime == 0)
            amount = 0;
        else
            constructTime--;
    }

    public void collect() {
        Warehouse warehouse = getGame().getWarehouse();
        while (amount > 0 && warehouse.addProduct(type, 1))
            amount--;
    }

    public int getAmount() {
        return amount;
    }
}
