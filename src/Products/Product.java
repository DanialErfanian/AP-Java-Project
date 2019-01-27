package Products;

import Logic.Constants;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Product {
    //    <Egg DepotSize="1" BuyCost="20" SaleCost="10"/>
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

    private Product(){
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

    public static final Product WOOL = getProductByName("wool");
    public static final Product MILK = getProductByName("milk");
    public static final Product EGG = getProductByName("egg");
    public static final Product BEAR = getProductByName("bear");
    public static final Product LION = getProductByName("lion");


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
}
