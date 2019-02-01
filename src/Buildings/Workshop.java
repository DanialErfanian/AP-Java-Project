package Buildings;

import Logic.Constants;
import Logic.Game;
import Products.GroundProduct;
import Products.Product;
import Utils.Position;
import Utils.WorkshopBuilder;

import java.util.ArrayList;

public class Workshop extends BaseBuilding {// TODO convert to enum :)
    private int runningThreads = 0;
    private int progress = 0;
    private int level = 1;
    private String name;
    private ArrayList<Product> inputs;
    private Product output;
    private Position position;
    private transient WorkshopBuilder builder;

    public Workshop(Game game, WorkshopBuilder workshopBuilder) {
        super(game);
        this.name = workshopBuilder.getName();
        this.inputs = workshopBuilder.getInputs();
        this.output = workshopBuilder.getOutput();
        this.position = workshopBuilder.getPosition();
        this.builder = workshopBuilder;

    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Workshop :" +
                "\nname: " + name +
                "\ninputProduct:" + inputs +
                "\noutputProduct:" + output +
                "\nnearestPosition:" + position +
                "\nlevel: " +
                level;
    }// get info

    public boolean upgrade() {
        int cost = getUpgradeCost();
        if (!getGame().decreaseMoney(cost))
            return false;
        level++;
        return true;
    }

    @Override
    protected int getUpgradeCost() {
        return Constants.WORKSHOP_UPGRADE_COST;
    }

    public String start() {
        if (runningThreads != 0)
            return "Workshop is working on another order!";
        int threads = level;
        Warehouse warehouse = getGame().getWarehouse();
        for (Product product : inputs)
            threads = Math.min(threads, warehouse.getProductCount(product));
        if (threads == 0)
            return "You haven't needed product!";
        for (Product product : inputs)
            warehouse.addProduct(product, -threads);
        runningThreads = threads;
        progress = Constants.WORKSHOP_PROGRESS;
        return "Workshop started handling order.";
    }

    public void increaseTurn() {
        progress--;
        if (progress == 0) {
            Position position = this.position;
            GroundProduct object = new GroundProduct(getGame(), output, position, runningThreads);
            getGame().getMap().addObject(position, object);
            runningThreads = 0;
        }
    }

    public WorkshopBuilder getBuilder() {
        return builder;
    }

    public int getLevel() {
        return level;
    }
}
