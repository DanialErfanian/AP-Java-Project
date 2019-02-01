package Logic;

import Animals.*;
import Buildings.Warehouse;
import Buildings.Well;
import Products.GroundProduct;
import Utils.Position;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Map extends MainObject {
    private int mapWidth;
    private int mapHeight;
    private Well well = new Well(getGame());
    private ArrayList<ArrayList<ArrayList<MiddleMapObject>>> objects;
    private double[][] grass;
    private int lastWildAnimalTime = 0;
    private int catLevel = 1;
    private Warehouse warehouse = new Warehouse(getGame());

    void relax() {
        for (int i = 0; i < mapWidth; i++)
            for (int j = 0; j < mapHeight; j++) {
                ArrayList<MiddleMapObject> objects = getObjects(i, j), relaxed = new ArrayList<>();
                assert (objects != null);
                for (MiddleMapObject object : objects)
                    if (object.isValid())
                        relaxed.add(object);
                objects.clear();
                objects.addAll(relaxed);
            }
    }

    @Override
    public String toString() {
        return "Map: " +
                "\nmapWidth: " + mapWidth +
                "\nmapHeight: " + mapHeight +
                "\nwell: " + well +
                "\nobjects: " + objects +
                "\ngrass: " + Arrays.deepToString(grass) +
                "\nwarehouse: " + warehouse;
    }


    boolean upgradeCats() {
        if (catLevel == 2)
            return false;
        int cost = Constants.CAT_UPGRADE_COST;
        if (!getGame().decreaseMoney(cost))
            return false;
        catLevel = 2;
        return true;
    }

    Well getWell() {
        return well;
    }

    public Position getRandomValidPosition() {
        Random random = new Random();
        int x = random.nextInt(mapWidth);
        int y = random.nextInt(mapHeight);
        return new Position(x, y);
    }

    private boolean isInvalid(int x, int y) {
        return x < 0 || y < 0 || x >= mapWidth || y >= mapHeight;
    }

    private boolean isInvalid(Position position) {
        return isInvalid(position.getX(), position.getY());
    }

    boolean collect(Position position) {
        ArrayList<MiddleMapObject> objects = getObjects(position);
        if (objects == null)
            return false;
        for (MiddleMapObject object : objects)
            if (object instanceof GroundProduct)
                ((GroundProduct) object).collect();
        return true;
    }

    @Nullable
    private ArrayList<MiddleMapObject> getObjects(Position position) {
        return getObjects(position.getX(), position.getY());
    }

    @Nullable
    private ArrayList<MiddleMapObject> getObjects(int x, int y) {
        if (isInvalid(x, y))
            return null;
        return this.objects.get(x).get(y);
    }

    Warehouse getWarehouse() {
        return warehouse;
    }

    private void addObject(int x, int y, MiddleMapObject object) {
        objects.get(x).get(y).add(object);
    }

    public void addObject(Position position, MiddleMapObject object) {
        addObject(position.getX(), position.getY(), object);
    }

    public double getGrass(Position position) {
        return grass[position.getX()][position.getY()];
    }

    Map(Game game, int mapWidth, int mapHeight) {
        super(game);
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        objects = new ArrayList<>(mapWidth);
        for (int i = 0; i < mapWidth; i++) {
            objects.add(new ArrayList<>(mapHeight));
            for (int j = 0; j < mapHeight; j++)
                objects.get(i).add(new ArrayList<>());
        }
        grass = new double[mapWidth][mapHeight];
    }

    @Override
    protected void increaseTurn() {
        well.increaseTurn();
        for (MiddleMapObject object : getObjects())
            if (object != null && object.isValid())
                object.increaseTurn();
        lastWildAnimalTime--;
        if (-lastWildAnimalTime == Constants.WILD_ANIMAL_TIME_PERIOD) {
            Position position = getRandomValidPosition();
            addObject(position, new WildAnimal(getGame(), position));
            position = getRandomValidPosition();
            addObject(position, new WildAnimal(getGame(), position));
            lastWildAnimalTime = 0;
        }
    }

    boolean cage(Position position) {
        ArrayList<MiddleMapObject> objects = getObjects(position);
        if (objects == null)
            return false;
        for (int i = 0; i < objects.size(); i++) {
            MiddleMapObject object = objects.get(i);
            if (object instanceof WildAnimal) {
                objects.set(i, ((WildAnimal) object).cage());
                System.out.println("We catched a Wild animal");
            }
        }
        return true;
    }

    private ArrayList<MiddleMapObject> getCellObjects(int x, int y) {
        return objects.get(x).get(y);
    }

    public ArrayList<MiddleMapObject> getCellObjects(Position position) {
        return getCellObjects(position.getX(), position.getY());
    }

    boolean decreasePlant(int x, int y) {
        double rate = Constants.PRODUCER_ANIMAL_EAT_GRASS_RATE;
        if (grass[x][y] < rate)
            return false;
        grass[x][y] -= rate;
        return true;
    }

    public Position getRandomGrassyPosition() {
        ArrayList<Position> positions = new ArrayList<>();
        for (int i = 0; i < mapWidth; i++)
            for (int j = 0; j < mapHeight; j++)
                if (grass[i][j] > 0)
                    positions.add(new Position(i, j));
        if (positions.size() == 0)
            return null;
        return positions.get(new Random().nextInt(positions.size()));
    }

    public Targetable getRandomCatchableTarget() {
        ArrayList<Targetable> targets = new ArrayList<>();

        for (int i = 0; i < mapWidth; i++)
            for (int j = 0; j < mapHeight; j++)
                for (MiddleMapObject object : objects.get(i).get(j))
                    if (object instanceof ProducerAnimal)
                        targets.add(object);
        if (targets.size() == 0)
            return null;
        return targets.get(new Random().nextInt(targets.size()));
    }

    public WildAnimal getRandomWildAnimal() {
        ArrayList<WildAnimal> animals = new ArrayList<>();

        for (int i = 0; i < mapWidth; i++)
            for (int j = 0; j < mapHeight; j++)
                for (MiddleMapObject object : objects.get(i).get(j))
                    if (object instanceof WildAnimal)
                        animals.add((WildAnimal) object);
        if (animals.size() == 0)
            return null;
        return animals.get(new Random().nextInt(animals.size()));
    }

    public GroundProduct getGroundProductForCat(Position position) {
        ArrayList<GroundProduct> products = new ArrayList<>();
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < mapWidth; i++)
            for (int j = 0; j < mapHeight; j++)
                for (MiddleMapObject object : objects.get(i).get(j))
                    if (object != null && object.isValid() && object instanceof GroundProduct) {
                        products.add((GroundProduct) object);
                        minDistance = Math.min(minDistance, Position.getDistance(position, object.getPosition()));
                    }
        if (catLevel == 2)
            for (GroundProduct product : products)
                if (Position.getDistance(position, product.getPosition()) == minDistance)
                    return product;
        if (products.size() == 0)
            return null;
        return products.get(new Random().nextInt(products.size()));
    }

    boolean plant(Position position) {
        if (isInvalid(position) || !getWell().plant())
            return false;
        grass[position.getX()][position.getY()] += Constants.GRASS_PLANT_INCREASE;
        return true;
    }

    boolean buy(String animalName) {
        int cost;
        BaseAnimal animal;
        Position position = getRandomValidPosition();
        switch (animalName) {
            case "hen":
                cost = Constants.HEN_BUY_COST;
                animal = new ProducerAnimal(getGame(), position, ProducerAnimalType.HEN);
                break;
            case "sheep":
                cost = Constants.SHEEP_BUY_COST;
                animal = new ProducerAnimal(getGame(), position, ProducerAnimalType.SHEEP);
                break;
            case "cow":
                cost = Constants.COW_BUY_COST;
                animal = new ProducerAnimal(getGame(), position, ProducerAnimalType.COW);
                break;
            case "cat":
                cost = Constants.CAT_BUY_COST;
                animal = new Cat(getGame(), position);
                break;
            case "dog":
                cost = Constants.DOG_BUY_COST;
                animal = new Dog(getGame(), position);
                break;
            default:
                return false;
        }
        if (!getGame().decreaseMoney(cost))
            return false;
        addObject(position, animal);
        return true;
    }

    public void moveAnimal(MiddleMapObject object, Position newPosition) {
        ArrayList<MiddleMapObject> objects = getObjects(object.getPosition());
        assert objects != null;
        objects.remove(object);
        addObject(newPosition, object);
        object.setPosition(newPosition);
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public ArrayList<MiddleMapObject> getObjects() {
        ArrayList<MiddleMapObject> result = new ArrayList<>();
        for (int i = 0; i < mapWidth; i++)
            for (int j = 0; j < mapHeight; j++)
                result.addAll(objects.get(i).get(j));
        return result;
    }
}
