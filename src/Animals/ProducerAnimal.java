package Animals;

import Logic.Game;
import Products.Product;

abstract public class ProducerAnimal extends BaseAnimal {
    final int maxHungriness = 100;
    private int productionRate, currentProgress, hungriness;

    public ProducerAnimal(Game game) {
        super(game);
    }

    abstract Product getProduct();

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
}
