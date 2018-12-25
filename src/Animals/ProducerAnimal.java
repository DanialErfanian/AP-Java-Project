package Animals;

import Logic.Constants;
import Logic.Game;
import Logic.Map;
import Products.GroundProduct;
import Products.Product;
import Utils.Position;

public class ProducerAnimal extends BaseAnimal {
    // if hungriness equals to maxHungriness  animal must die
    private int productionRate, currentProgress, hungriness;
    private ProducerAnimalType type;

    public ProducerAnimal(Game game, Position position, ProducerAnimalType type) {
        super(game, position);
        this.type = type;
        this.productionRate = Constants.PRODUCER_ANIMAL_PRODUCTION_RATE;
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

    @Override
    public void regenerateTarget() {
        this.target = getGame().getMap().getRandomGrassyPosition();
    }

    private void eat() {
        int maxHungriness = Constants.PRODUCER_ANIMAL_MAX_HUNGRINESS;
        if (hungriness >= maxHungriness)
            die();
        else {
            hungriness++;
            if (hungriness > 0 && getGame().decreasePlant(getPosition()))
                hungriness--;
        }
    }

    @Override
    void doTask() {
        eat();
        if (this.isValid())
            decreaseProgress();
    }

    private void decreaseProgress() {
        currentProgress -= productionRate;
        if (currentProgress <= 0) {
            Map map = getGame().getMap();
            map.addObject(getPosition(), new GroundProduct(getGame(), getProduct(), getPosition(), 1));
            currentProgress = Constants.PRODUCER_ANIMAL_FULL_PROGRESS;
        }
    }

    @Override
    public String toString() {
        return "ProducerAnimal: " +
                "\nproductionRate: " +
                productionRate +
                "\ncurrentProgress: " +
                currentProgress +
                "\nhungriness: " +
                hungriness +
                "\ntype: " +
                type +
                super.toString();
    }

    protected boolean isTargetInvalid() {
        if(super.isTargetInvalid())
            return true;
        return getGame().getMap().getGrass(target.getPosition()) > 0;
    }


}
