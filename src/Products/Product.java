package Products;

import Logic.Constants;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Product {
    private int depotSize, buyCost, saleCost;
    private String name;

    private static Product[] products;

    private Product(int depotSize, int buyCost, int saleCost, String name) {
        this.depotSize = depotSize;
        this.buyCost = buyCost;
        this.saleCost = saleCost;
        this.name = name;
    }

    static {
        loadProducts();
    }

    private static void loadProducts() {
        File file = Constants.PRODUCTS_FILE;
        System.out.println("Load products from: " + file);
        try {
            YaGson mapper = new YaGsonBuilder().setPrettyPrinting().create();
            String json = new String(Files.readAllBytes(Paths.get(file.toString())));
            products = mapper.fromJson(json, Product[].class);
            System.out.println("Loading products done.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Loading products failed.");
            System.exit(0);
        }
    }

    public static final Product WOOL = getProductByName("Wool");
    public static final Product MILK = getProductByName("Milk");
    public static final Product EGG = getProductByName("Egg");
    public static final Product BEAR = getProductByName("CagedBrownBear");
    public static final Product LION = getProductByName("CagedLion");


    public int getSaleCost() {
        return this.saleCost;
    }

    public int getBuyCost() {
        return this.buyCost;
    }

    private static Product getProductByName(String name) {
        for (Product product : products)
            if (product.name.equals(name))
                return product;
        return null;
    }

    int getDepotSize() {
        return depotSize;
    }

    public static Product[] getProducts() {
        return products;
    }
}
