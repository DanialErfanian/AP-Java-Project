package Logic;

import Products.Product;
import Products.WorkshopBuilder;

import java.util.Arrays;
import java.util.HashMap;

public class Level implements java.io.Serializable {
    private String name;
    private HashMap<Product, Integer> requirements;
    private final WorkshopBuilder[] workshops = new WorkshopBuilder[6];
    private int mapWidth;
    private int mapHeight;
    private int money;

    static Level loadFromFile(String path) {
        // TODO
        return null;
    }

    @Override
    public String toString() {
        return "Level: " +
                "\nname: " + name +
                "\nrequirements: " + requirements +
                "\nworkshops: " + Arrays.toString(workshops) +
                "\nmapWidth: " + mapWidth +
                "\nmapHeight: " + mapHeight +
                "\nmoney: " + money;
    }

    public Level(String name, HashMap<Product, Integer> requirements, int mapWidth, int mapHeight, int money) {
        this.name = name;
        this.requirements = requirements;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.money = money;
    }

    String getName() {
        return name;
    }

    HashMap<Product, Integer> getRequirements() {
        return requirements;
    }

    WorkshopBuilder[] getWorkshops() {
        return workshops;
    }

    int getMapWidth() {
        return mapWidth;
    }

    int getMapHeight() {
        return mapHeight;
    }

    int getMoney() {
        return money;
    }
}
