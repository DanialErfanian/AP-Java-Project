package Logic;

import Animals.WildAnimal;
import Buildings.Warehouse;
import Buildings.Well;
import Products.GroundProduct;
import Utils.Position;

import java.util.ArrayList;
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

    boolean upgradeCat() {
        if(catLevel == 2)
            return false;
        int cost = Constants.CAT_UPGRADE_COST;
        if(!getGame().decreaseMoney(cost))
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

    void collect(int x, int y) {
        ArrayList<MiddleMapObject> objects = this.objects.get(x).get(y);
        for (MiddleMapObject object : objects)
            if (object instanceof GroundProduct)
                ((GroundProduct) object).collect();
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


    public Map(Game game, int mapWidth, int mapHeight) {
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
        lastWildAnimalTime--;
        if (-lastWildAnimalTime == Constants.WILD_ANIMAL_TIME_PERIOD) {
            Position position = getRandomValidPosition();
            addObject(position, new WildAnimal(getGame(), position));
            position = getRandomValidPosition();
            addObject(position, new WildAnimal(getGame(), position));
            lastWildAnimalTime = 0;
        }
        well.increaseTurn();
        for (int i = 0; i < mapWidth; i++)
            for (int j = 0; j < mapHeight; j++)
                for (MiddleMapObject object : objects.get(i).get(j))
                    object.increaseTurn();
    }

    void cage(int x, int y) {
        ArrayList<MiddleMapObject> objects = this.objects.get(x).get(y);
        for (int i = 0; i < objects.size(); i++) {
            MiddleMapObject object = objects.get(i);
            if (object instanceof WildAnimal)
                objects.set(i, ((WildAnimal) object).cage());
        }
    }

    private ArrayList<MiddleMapObject> getCellObjects(int x, int y) {
        return objects.get(x).get(y);
    }

    public ArrayList<MiddleMapObject> getCellObjects(Position position) {
        return getCellObjects(position.getX(), position.getY());
    }

    boolean upgradeCats() {
        catLevel++;
        return false;
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
                    if (!(object instanceof WildAnimal))
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

    @Override
    public String toString() {
        // TODO
        return super.toString();
    }

    public GroundProduct getGroundProductForCat(Position position) {
        ArrayList<GroundProduct> products = new ArrayList<>();
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < mapWidth; i++)
            for (int j = 0; j < mapHeight; j++)
                for (MiddleMapObject object : objects.get(i).get(j))
                    if (object instanceof GroundProduct) {
                        products.add((GroundProduct) object);
                        minDistance = Math.min(minDistance, Position.getDistance(position, object.position));
                    }
        if (catLevel == 2)
            for (GroundProduct product : products)
                if (Position.getDistance(position, product.position) == minDistance)
                    return product;
        if (products.size() == 0)
            return null;
        return products.get(new Random().nextInt(products.size()));
    }
}
