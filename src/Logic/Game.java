package Logic;

import Buildings.Warehouse;
import Buildings.Workshop;
import Products.Product;
import Transportation.Helicopter;
import Transportation.Truck;
import Transportation.Vehicle;
import Utils.Position;

import java.io.*;
import java.util.Arrays;

public class Game implements java.io.Serializable {
    // TODO Max level is still unhandled
    private int money = Constants.START_MONEY;
    private Workshop[] workshops = new Workshop[6];
    private Level currentLevel;
    private Map map;
    private Truck truck = new Truck(this);
    private Helicopter helicopter = new Helicopter(this);

    public Game() {

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

    public void addWorkshop(Workshop workshop) {
        //TODO: take care of the number of warehouses
    }

    @Override
    public String toString() {
        // TODO
        return null;
    }

    public boolean save(String path) {
        relax();
        try {
            FileOutputStream file = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(this);

            out.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static Game load(String path) {
        Game resultGame;
        try {
            FileInputStream file = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            resultGame = (Game) in.readObject();

            in.close();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return resultGame;
    }

    public void run(String mapName) {
    }

    public void loadCustom(String path) {
    }

    public boolean buy(String animalName) {
        return map.buy(animalName);
    }

    public void collect(int x, int y) {
        map.collect(x, y);
    }

    public void cage(int x, int y) {
        map.cage(x, y);
    }

    public boolean plant(int x, int y) {
        return map.plant(x, y);
    }

    public boolean well() {
        return map.getWell().fill();
    }

    private Workshop getWorkshopWithName(String workshopName) {
        for (Workshop workshop : workshops)
            if (workshop != null && workshop.getName().equals(workshopName))
                return workshop;
        return null;
    }

    public String startWorkshop(String workshopName) {
        Workshop workshop = getWorkshopWithName(workshopName);
        if (workshop != null)
            return workshop.start();
        return "Workshop with this name not found!";
    }

    public boolean upgrade(String targetName) {
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
                return ""; // TODO why levels ? no level ?
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

    private Vehicle getVehicleByName(String name) {
        switch (name) {
            case "truck":
                return truck;
            case "helicopter":
                return helicopter;
            default:
                return null;
        }
    }

    private Product getProductByName(String itemName) {
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
