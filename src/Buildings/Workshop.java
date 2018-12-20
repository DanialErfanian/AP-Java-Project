package Buildings;

import Logic.Constants;
import Logic.Game;
import Products.GroundProduct;
import Products.Product;
import Utils.Position;

import java.util.ArrayList;

public class Workshop extends BaseBuilding {
    private String name;
    private int runningThreads = 0;
    private int progress = 0;
    private ArrayList<Product> inputProducts;
    private Product outputProduct;
    private int level = 1;

    public Workshop(Game game, ArrayList<Product> inputProducts, Product outputProduct, String name) {
        super(game);
        this.inputProducts = inputProducts;
        this.outputProduct = outputProduct;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return null;
    }// get info

    public boolean upgrade() {
        int cost = Constants.WORKSHOP_UPGRADE_COST;
        if (!getGame().decreaseMoney(cost))
            return false;
        level++;
        return true;
    }

    public String start() {
        if (runningThreads != 0)
            return "Workshop is working on another order!";
        int threads = level;
        Warehouse warehouse = getGame().getWarehouse();
        for (Product product : inputProducts)
            threads = Math.min(threads, warehouse.getProductCount(product));
        if (threads == 0)
            return "You haven't needed product!";
        for (Product product : inputProducts)
            warehouse.addProduct(product, -threads);
        runningThreads = threads;
        progress = Constants.WORKSHOP_PROGRESS;
        return "Workshop started handling order.";
    }

    private Position getNearestPosition() {
        // TODO
        return null;
    }

    public void increaseTurn() {
        progress--;
        if (progress == 0) {
            Position position = getNearestPosition();
            GroundProduct object = new GroundProduct(getGame(), outputProduct, position, runningThreads);
            getGame().getMap().addObject(position, object);
            runningThreads = 0;
        }
    }


}
