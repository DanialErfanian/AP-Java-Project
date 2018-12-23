package Logic;

import Buildings.Warehouse;
import Buildings.Workshop;
import Transportation.Helicopter;
import Transportation.Truck;
import Utils.Position;

public class Game {
    private int money = Constants.START_MONEY;
    private Workshop[] workshops = new Workshop[6];
    private Level currentLevel;
    private Map map;
    private Truck truck;
    private Helicopter helicopter;

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

    public void setMoney(int money) {
        this.money = money;
    }

    private Workshop getWorkshop(int index) {
        return workshops[index];
    }

    public void addWorkshop(Workshop workshop) {
        //TODO: take care of the number of warehouses
    }

    @Override
    public String toString() {
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

    public static void decrasePlant(Position position) {
    }

    public void buy(String animalName) {
        // TODO
//        int cost = -1;
//        BaseAnimal animal = null;
//
//        switch (animalName) {
//            case "Hen":
//                cost = Constants.HEN_BUY_COST;
//                animal = new Hen(this, )
//        }
    }

    public void collect(int x, int y) {
        map.collect(x, y);
    }

    public void cage(int x, int y) {
        map.cage(x, y);
    }

    public void plant(int x, int y) {

    }

    public void well() {
        map.getWell().fill();
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
                return map.getCat().upgrade();
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

    }

}
