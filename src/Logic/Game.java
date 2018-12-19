package Logic;

import Buildings.BaseBuilding;
import Buildings.Warehouse;
import Buildings.Workshop;
import Utils.Position;

public class Game {
    private int money = Constants.START_MONEY;
    private Workshop[] workshops = new Workshop[6];
    private Level currentLevel;
    private Map map;

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

    public void inceaseTurn() {
        map.increaseTurn();
        for (int i = 0; i < workshops.length; i++)
            getWorkshop(i).increaseTurn();
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public BaseBuilding getWorkshop(int index) {
        return workshops[index];
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

    private Game() {

    }

    public static void decrasePlant(Position position) {

    }

    public void buy(String animalName) {

    }

}
