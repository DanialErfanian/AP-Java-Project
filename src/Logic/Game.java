package Logic;

import Animals.Cat;
import Animals.Dog;
import Buildings.BaseBuilding;

import java.util.ArrayList;

public class Game {
    private Cat cat;
    private Dog dog;
    private BaseBuilding[] buildings = new BaseBuilding[6];
    private Level currentLevel;
    //private ArrayList<Level> levels;

    public BaseBuilding getBuilding(int index) {
        return null;
    }

    public void AddBuilding(BaseBuilding building) {
        //TODO: take care of the number of warehouses
    }

    public String getInfo() {
        return null;
    }

    public void save(String path) {
    }

    public void load(String path) {
    }

    public void run(String mapName) {
    }

    public void loadCustom(String path) {
    }
}
