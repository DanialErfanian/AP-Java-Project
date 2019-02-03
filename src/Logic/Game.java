package Logic;

import Animals.AnimalType;
import Buildings.Warehouse;
import Buildings.Workshop;
import Products.Product;
import Transportation.Helicopter;
import Transportation.Truck;
import Transportation.Vehicle;
import Utils.Position;
import Utils.WorkshopBuilder;
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
import java.util.Arrays;
import java.util.HashMap;


public class Game implements java.io.Serializable {
    // TODO Helicopter drop project on middle map not to warehouse
    // TODO Max level is still unhandled
    private HashMap<Product, Integer> requirements;
    private int money;
    private final Workshop[] workshops = new Workshop[6];
    private Map map;
    private Truck truck;
    private Helicopter helicopter;
    private final Level level;

    public Game(Level level) {
        this.level = level;
        requirements = level.getRequirements();
        money = level.getMoney();
        WorkshopBuilder[] workshops = level.getWorkshops();
        for (int i = 0; i < workshops.length; i++)
            if (workshops[i] != null)
                this.workshops[i] = new Workshop(this, workshops[i]);
            else
                this.workshops[i] = null;
        this.map = new Map(this, level.getMapWidth(), level.getMapHeight());
        this.truck = new Truck(this);
        this.helicopter = new Helicopter(this);
    }

    public Level getLevel() {
        return level;
    }

    private void done() {
        requirements = null;
        money = 0;
        for (int i = 0; i < 6; i++) workshops[i] = null;
        map = null;
        truck = null;
        helicopter = null;
        System.out.println("Level is done. GL&HF :)");
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
        HashMap<Product, Double> passedRequirements = getPassedPercent();
        boolean passed = true;
        for (HashMap.Entry<Product, Double> x : passedRequirements.entrySet())
            passed &= (x.getValue().equals(100.0));
        if (passed) {
            done();
            return;
        }
        // TODO: max Level? :thinking:
        if (map == null)
            return;
        map.increaseTurn();
        for (Workshop workshop : workshops)
            if (workshop != null)
                workshop.increaseTurn();
        this.truck.increaseTurn();
        this.helicopter.increaseTurn();
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
        if (path == null || map == null)
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

    public boolean buy(String animalName) {
        return map.buy(animalName);
    }

    public boolean buy(AnimalType type) {
        return buy(type.toString().toLowerCase());
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
        map.relax();
    }

    private boolean decreasePlant(int x, int y) {
        return map.decreasePlant(x, y);
    }

    public boolean decreasePlant(Position position) {
        return decreasePlant(position.getX(), position.getY());
    }

    public String print(String target) {
        if (map == null)
            return "Game isn't started with certain level";
        switch (target) {
            case "info":
                return this.toString();
            case "map":
                return map.toString();
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

    public boolean addToVehicle(String name, String itemName, int count) {
        Product product = Product.getProductByName(itemName);
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

    public Workshop[] getWorkshops() {
        return workshops;
    }

    public Truck getTruck() {
        return truck;
    }

    public Helicopter getHelicopter() {
        return helicopter;
    }

    public int getAnimalBuyCost(AnimalType type) {
        switch (type) {
            case Hen:
                return Constants.HEN_BUY_COST;
            case Sheep:
                return Constants.SHEEP_BUY_COST;
            case Cow:
                return Constants.COW_BUY_COST;
            case Cat:
                return Constants.CAT_BUY_COST;
            case Dog:
                return Constants.DOG_BUY_COST;
        }
        return 0;
    }
}