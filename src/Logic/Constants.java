package Logic;

import java.io.File;

public class Constants {
    private Constants() {
    }

    public static final File PRODUCTS_FILE = new File("");//TODO

    public static final double NEW_PLANT_NEEDED_WATER = 1;

    static final int CAT_UPGRADE_COST = 500; // coin

    static final double PRODUCER_ANIMAL_EAT_GRASS_RATE = 1;
    public static final int PRODUCER_ANIMAL_FULL_PROGRESS = 10;
    public static final int PRODUCER_ANIMAL_PRODUCTION_RATE = 1;
    public static final int PRODUCER_ANIMAL_MAX_HUNGRINESS = 13;

    public static final int HELICOPTER_INITIAL_CAPACITY = 15;
    public static final int HELICOPTER_UPGRADE_COST = 100; // coin
    public static final int HELICOPTER_UPGRADE_INCREASE_CAPACITY = 10;
    public static final int HELICOPTER_JOB_PROGRESS = 30; // turn

    public static final int TRUCK_INITIAL_CAPACITY = 15;
    public static final int TRUCK_UPGRADE_COST = 100; // coin
    public static final int TRUCK_UPGRADE_INCREASE_CAPACITY = 10;
    public static final int TRUCK_JOB_PROGRESS = 30; // turn

    public static final int GROUND_PRODUCT_TIMEOUT = 5;

    static final int HEN_BUY_COST = 100; // coin
    static final int SHEEP_BUY_COST = 150; // coin
    static final int COW_BUY_COST = 200; // coin
    static final int DOG_BUY_COST = 500; // coin
    static final int CAT_BUY_COST = 500; // coin

    public static final int WELL_INITIAL_CAPACITY = 10;
    public static final int WELL_INITIAL_REFILL_COST = 15;
    public static final int WELL_UPGRADE_INCREASE_REFILL_COST = 10;
    public static final int WELL_UPGRADE_INCREASE_CAPACITY = 5;
    public static final int WELL_UPGRADE_COST = 100; // coin

    public static final int WAREHOUSE_INITIAL_CAPACITY = 30;
    public static final int WAREHOUSE_UPGRADE_COST = 100; //coin
    public static final int WAREHOUSE_UPGRADE_INCREASE_CAPACITY = 10;

    public static final int WORKSHOP_PROGRESS = 15; // turn
    public static final int WORKSHOP_UPGRADE_COST = 150;

    static final int WILD_ANIMAL_TIME_PERIOD = 2 * 60; // 2 minute
    static final int START_MONEY = 500;

}
