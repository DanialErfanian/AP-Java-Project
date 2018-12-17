package Logic;

import Animals.Cat;
import Animals.Dog;

import java.util.ArrayList;

public class Map extends MainObject {
    private int mapWidth;
    private int mapHeight;
    private Cat cat;
    private Dog dog;
    private ArrayList<MiddleMapObject>[][] objects;
    private double[][] grass;


    public Map(Game game, int mapWidth, int mapHeight) {
        super(game);
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
//        objects = new ArrayList[mapWidth][];
//        for (int i = 0; i < mapWidth; i++)
//            for (int j = 0; j < mapHeight; j++) {
//
//            }

    }

    @Override
    protected void increaseTurn() {

    }
}
