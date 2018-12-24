package Logic;

import Animals.Cat;
import Animals.Dog;
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
    private Well well = new Well(getGame());
    private ArrayList<ArrayList<ArrayList<MiddleMapObject>>> objects;
    private double[][] grass;
    private int lastWildAnimalTime = 0;
    private Warehouse warehouse = new Warehouse(getGame());

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


    public Position getRandomValidAdjacentPosition(Position position) {
        int dx[] = {-1, -0, +0, +1};
        int dy[] = {-0, -1, +1, +0};
        ArrayList<Position> validPositions = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Position currentPosition = new Position(position.getX() + dx[i], position.getY() + dy[i]);
            if (this.isValid(currentPosition))
                validPositions.add(currentPosition);
        }
        return validPositions.get(new Random().nextInt(validPositions.size()));
    }

    private boolean isValid(Position position) {
        return position.getX() >= 0 &&
                position.getX() < this.mapWidth &&
                position.getY() >= 0 &&
                position.getY() < this.mapHeight;
    }
}
