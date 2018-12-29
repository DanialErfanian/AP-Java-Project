package Logic;

import Buildings.Warehouse;
import Buildings.Workshop;
import Products.Product;
import Products.WorkshopBuilder;
import Transportation.Helicopter;
import Transportation.Truck;
import Transportation.Vehicle;
import Utils.Position;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

// TODO print map without start NuLL Pointer exception
public class Game implements java.io.Serializable {
    // TODO Max level is still unhandled
    private static ArrayList<Level> levels = new ArrayList<>();
    private HashMap<Product, Integer> requirements;
    private int money;
    private final Workshop[] workshops = new Workshop[6];
    private Map map;
    private Truck truck;
    private Helicopter helicopter;

    public Game() {

    }

    private HashMap<Product, Double> getPassedPercent() {
        HashMap<Product, Double> result = new HashMap<>();
        for (HashMap.Entry<Product, Integer> entry : requirements.entrySet()) {
            Product product = entry.getKey();
            int wishCount = entry.getValue();
            result.put(product, (double) (100 * getWarehouse().getProductCount(product) / wishCount));
        }
        return result;
    }

    public Game(Level level) {
        requirements = level.getRequirements();
        money = level.getMoney();
        WorkshopBuilder[] workshops = level.getWorkshops();
        for (int i = 0; i < 6; i++)
            if (workshops[i] != null)
                this.workshops[i] = new Workshop(this, workshops[i]);
            else
                this.workshops[i] = null;
        this.map = new Map(this, level.getMapWidth(), level.getMapHeight());
        this.truck = new Truck(this);
        this.helicopter = new Helicopter(this);
    }

    public Map getMap() {
        return map;
    }

    public Warehouse getWarehouse() {
        return map.getWarehouse();
    }

    public boolean decreaseMoney(int x) {
        if (money < x)
            return false;
        money -= x;
        return true;
    }

    public void increaseMoney(int x) {
        money += x;
    }

    public void increaseTurn() {
        // TODO: check if level requirements satisfied level is done
        // TODO: max Level? :thinking:
        map.increaseTurn();
        for (Workshop workshop : workshops) workshop.increaseTurn();
    }

    public void turn(int count) {
        for (int i = 0; i < count; i++)
            increaseTurn();
    }

    public int getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return "Game: " +
                "\nmoney: " + money +
                "\nrequirements: " + requirements +
                "\nrequirements passed percent: " + this.getPassedPercent();
    }

    public boolean save(String path) {
        if (path == null)
            return false;
        relax();
        YaGson mapper = new YaGsonBuilder().setPrettyPrinting().create();
        try {
            File file = new File(path);
            PrintStream result = new PrintStream(file);
            result.println(mapper.toJson(this, Game.class));
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Nullable
    public static Game load(String path) {
        if (path == null)
            return null;
        try {
            String text = new String(Files.readAllBytes(Paths.get(path)));
            YaGson mapper = new YaGsonBuilder().setPrettyPrinting().create();
            return mapper.fromJson(text, Game.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    public static Game run(String mapName) {
        if (mapName == null)
            return null;
        for (Level level : levels)
            if (level.getName().equals(mapName))
                return new Game(level);
        return null;
    }

    public static boolean loadCustom(String path) {
        if (path == null)
            return false;
        Level level = Level.loadFromFile(path);
        if (level == null)
            return false;
        levels.add(level);
        return true;
    }

    public boolean buy(String animalName) {
        return map.buy(animalName);
    }

    public boolean collect(Position position) {
        return map.collect(position);
    }

    public boolean cage(Position position) {
        return map.cage(position);
    }

    public boolean plant(Position position) {
        return map.plant(position);
    }

    public boolean well() {
        return map.getWell().fill();
    }

    @Nullable
    private Workshop getWorkshopWithName(String workshopName) {
        for (Workshop workshop : workshops)
            if (workshop != null && workshop.getName().equals(workshopName))
                return workshop;
        return null;
    }

    public String startWorkshop(String workshopName) {
        if (workshopName == null)
            return "Invalid input!";
        Workshop workshop = getWorkshopWithName(workshopName);
        if (workshop != null)
            return workshop.start();
        return "Workshop with this name not found!";
    }

    public boolean upgrade(String targetName) {
        if (targetName == null)
            return false;
        switch (targetName) {
            case "cat":
                return map.upgradeCats();
            case "well":
                return map.getWell().upgrade();
            case "truck":
                return truck.upgrade();
            case "helicopter":
                return helicopter.upgrade();
            case "warehouse":
                return getWarehouse().upgrade();
            default:
                Workshop workshop = getWorkshopWithName(targetName);
                if (workshop != null)
                    return workshop.upgrade();
        }
        return false;
    }

    private void relax() {
        // TODO
        // GroundProduct with amount 0 mean invalid and must be deleted
        // WildAnimal will replace with null after remove
        // generally BaseAnimal with position = null mean invalid and must be deleted
    }

    private boolean decreasePlant(int x, int y) {
        return map.decreasePlant(x, y);
    }

    public boolean decreasePlant(Position position) {
        return decreasePlant(position.getX(), position.getY());
    }

    public String print(String target) {
        switch (target) {
            case "info":
                return this.toString();
            case "map":
                return map.toString();
            case "levels":
                return levels.toString();
            case "warehouse":
                return getWarehouse().toString();
            case "well":
                return map.getWell().toString();
            case "workshops":
                return Arrays.toString(workshops);
            case "truck":
                return truck.toString();
            case "helicopter":
                return helicopter.toString();
        }
        return "Invalid input!";
    }

    @Nullable
    @Contract(pure = true)
    private Vehicle getVehicleByName(@NotNull String name) {
        switch (name) {
            case "truck":
                return truck;
            case "helicopter":
                return helicopter;
            default:
                return null;
        }
    }

    @Nullable
    @Contract(pure = true)
    private Product getProductByName(@NotNull String itemName) {
        switch (itemName) {
            case "wool":
                return Product.WOOL;
            case "egg":
                return Product.EGG;
            case "milk":
                return Product.MILK;
            default:
                return null;
        }
    }

    public boolean addToVehicle(String name, String itemName, int count) {
        Product product = getProductByName(itemName);
        Vehicle vehicle = getVehicleByName(name);
        if (product == null)
            return false;
        if (vehicle == null)
            return false;
        return vehicle.addProduct(product, count);
    }

    public boolean clearVehicle(String name) {
        Vehicle vehicle = getVehicleByName(name);
        if (vehicle == null)
            return false;
        return vehicle.clear();
    }

    public boolean goVehicle(String name) {
        Vehicle vehicle = getVehicleByName(name);
        if (vehicle == null)
            return false;
        return vehicle.go();
    }

}
