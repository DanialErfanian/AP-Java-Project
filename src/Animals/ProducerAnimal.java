package Animals;

import Logic.Constants;
import Logic.Game;
import Logic.Map;
import Products.GroundProduct;
import Products.Product;
import Utils.Position;

public class ProducerAnimal extends BaseAnimal {
    // if hungriness equals to maxHungriness  animal must destruct
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
                return Product.Milk;
            case SHEEP:
                return Product.Wool;
            case HEN:
                return Product.Egg;
        }
        return null;
    }

    @Override
    public void regenerateTarget() {
        this.target = getGame().getMap().getRandomGrassyPosition();
    }

    private boolean eat() {
        int maxHungriness = Constants.PRODUCER_ANIMAL_MAX_HUNGRINESS;
        if (hungriness >= maxHungriness) {
            destruct();
            deathState();
        } else {
            double decreaseHungriness = Constants.PRODUCER_ANIMAL_EAT_GRASS_DECREASE_HUNGRINESS;
            hungriness++;
            if (hungriness > decreaseHungriness && getGame().decreasePlant(getPosition())) {
                hungriness -= decreaseHungriness;
                return true;
            }
        }
        return false;
    }

    @Override
    boolean doTask() {
        boolean result = eat();
        if (this.isValid())
            decreaseProgress();
        return result;
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
                type + "\n" +
                super.toString();
    }

    @Override
    public AnimalType getType() {
        if (type.equals(ProducerAnimalType.COW))
            return AnimalType.Cow;
        else if (type.equals(ProducerAnimalType.SHEEP))
            return AnimalType.Sheep;
        else if (type.equals(ProducerAnimalType.HEN))
            return AnimalType.Hen;
        assert false;
        return null;
    }

}
