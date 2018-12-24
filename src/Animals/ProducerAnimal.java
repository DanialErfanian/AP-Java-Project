package Animals;

import Logic.Game;
import Products.Product;
import Utils.Position;

public class ProducerAnimal extends BaseAnimal {

    final int maxHungriness = 100;
    private int productionRate, currentProgress, hungriness;
    private ProducerAnimalType type;

    ProducerAnimal(Game game, Position position, ProducerAnimalType type) {
        super(game, position);
        this.type = type;
    }

    // return product type of this animal depending on its type!
    public Product getProduct() {
        switch (type) {
            case COW:
                return Product.MILK;
            case SHEEP:
                return Product.WOOL;
            case HEN:
                return Product.EGG;
        }
        return null;
    }

    public int getHungriness() {
        return hungriness;
    }

    public void setHungriness(int hungriness) {
        this.hungriness = hungriness;
    }

    public int getProductionRate() {
        return productionRate;
    }

    public void setProductionRate(int productionRate) {
        this.productionRate = productionRate;
    }

    public int getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(int currentProgress) {
        this.currentProgress = currentProgress;
    }

    final public void eat() {
        // decrease hungriness if chaman not found
        // TODO
    }

    @Override
    protected void increaseTurn() {
        // TODO
    }
}
