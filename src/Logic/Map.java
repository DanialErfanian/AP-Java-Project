package Logic;

import Animals.Cat;
import Animals.Dog;
import Animals.Lion;
import Utils.Position;

import java.util.ArrayList;
import java.util.Random;

public class Map extends MainObject {
    private int mapWidth;
    private int mapHeight;
    private Cat cat;
    private Dog dog;
    private ArrayList<ArrayList<ArrayList<MiddleMapObject>>> objects;
    private double[][] grass;
    private int lastWildAnimalTime = 0;

    private void addObject(int x, int y, MiddleMapObject object) {
        objects.get(x).get(y).add(object);
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
            Random random = new Random();
            int x = random.nextInt(mapWidth);
            int y = random.nextInt(mapHeight);
            // FIXME add lion or bear or both
            addObject(x, y, new Lion(getGame(), new Position(x, y)));
            addObject(x, y, new Lion(getGame(), new Position(x, y)));
            lastWildAnimalTime = 0;
        }
        cat.increaseTurn();
        dog.increaseTurn();
        for (int i = 0; i < mapWidth; i++)
            for (int j = 0; j < mapHeight; j++)
                for (MiddleMapObject object : objects.get(i).get(j))
                    object.increaseTurn();
    }
}
