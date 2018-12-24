package Logic;

import Animals.Cat;
import Animals.Dog;
import Animals.Lion;
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
    private Cat cat;
    private Dog dog;
    private Well well;
    private ArrayList<ArrayList<ArrayList<MiddleMapObject>>> objects;
    private double[][] grass;
    private int lastWildAnimalTime = 0;
    private Warehouse warehouse;

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

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void addObject(int x, int y, MiddleMapObject object) {
        objects.get(x).get(y).add(object);
    }

    public void addObject(Position position, MiddleMapObject object) {
        addObject(position.getX(), position.getY(), object);
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public double getGrass(int x, int y) {
        return grass[x][y];
    }

    public Cat getCat() {
        return cat;
    }

    public Dog getDog() {
        return dog;
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
    }

    @Override
    protected void increaseTurn() {
        lastWildAnimalTime--;
        if (-lastWildAnimalTime == Constants.WILD_ANIMAL_TIME_PERIOD) {
            Position position = getRandomValidPosition();
            int x = position.getX();
            int y = position.getY();
            // FIXME add lion or bear or both
            addObject(x, y, new Lion(getGame(), position));
            addObject(x, y, new Lion(getGame(), position));
            lastWildAnimalTime = 0;
        }
        cat.increaseTurn();
        dog.increaseTurn();
        well.increaseTurn();
        for (int i = 0; i < mapWidth; i++)
            for (int j = 0; j < mapHeight; j++)
                for (MiddleMapObject object : objects.get(i).get(j))
                    object.increaseTurn();
    }

    void cage(int x, int y) {
        ArrayList<MiddleMapObject> objects = this.objects.get(x).get(y);
        for (MiddleMapObject object : objects)
            if (object instanceof WildAnimal)
                ((WildAnimal) object).cage();
    }
}
