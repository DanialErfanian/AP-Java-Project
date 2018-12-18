package Logic;

import Buildings.BaseBuilding;
import Utils.Position;

public class Game {
    private int money = Constants.START_MONEY;
    private BaseBuilding[] buildings = new BaseBuilding[6];
    private Level currentLevel;
    private Map map;

    public void inceaseTurn(){
        map.increaseTurn();
        for(int i = 0 ; i < buildings.length; i ++)
            getBuilding(i).increaseTurn();
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public BaseBuilding getBuilding(int index) {
        return buildings[index];
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
