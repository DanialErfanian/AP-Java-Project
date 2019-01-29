package Logic;

import FarmFrenzy.GameUI.UIProperties;
import Products.Product;
import Utils.WorkshopBuilder;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

public class Level {
    private String name;
    private HashMap<Product, Integer> requirements;
    private final WorkshopBuilder[] workshops = new WorkshopBuilder[6];
    private int mapWidth;
    private int mapHeight;
    private int money;
    private String UIPropertiesPath;

    @Nullable
    public static Level readFromFile(String path) {
        try {
            System.out.println("Loading level from " + path);
            String text = new String(Files.readAllBytes(Paths.get(path)));
            YaGson mapper = new YaGsonBuilder().setPrettyPrinting().create();
            return mapper.fromJson(text, Level.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean writeToFile(String path) {
        YaGson mapper = new YaGsonBuilder().setPrettyPrinting().create();
        File file = new File(path);
        try {
            PrintStream result = new PrintStream(file);
            result.println(mapper.toJson(this, Level.class));
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
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

    public Level(String name, HashMap<Product, Integer> requirements, WorkshopBuilder[] workshops, int mapWidth, int mapHeight, int money, String UIPropertiesPath) {
        this.name = name;
        this.requirements = requirements;
        System.arraycopy(workshops, 0, this.workshops, 0, Math.min(6, workshops.length));
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.money = money;
        this.UIPropertiesPath = UIPropertiesPath;
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

    public String getUIPropertiesPath() {
        return UIPropertiesPath;
    }
}
