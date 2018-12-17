package Buildings;

import Logic.Game;
import Products.Product;

import java.util.ArrayList;

public class Workshop extends BaseBuilding {
    private int progress;
    private ArrayList<Product> inputProducts;
    private Product outputProduct;

    public Workshop(Game game) {
        super(game);
    }

    public String toString() {
        return null;
    }// get info

    public boolean upgrade() {
        return false;
    }

    public String start() {
        return null;
    }

    public void increaseTurn(){

    }


}
