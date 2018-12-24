package Utils;

import Products.Product;

import java.util.HashMap;

public class ProductPool {
    private int capacity;
    private int remainedCapacity;
    private HashMap<Product, Integer> products = new HashMap<>();
    //TODO: add json constructor


    public ProductPool(int capacity) {
        this.capacity = capacity;
        this.remainedCapacity = capacity;
    }

    public int getProductCount(Product product) {
        return products.getOrDefault(product, 0);
    }

    public boolean addProduct(Product product, int count) {
        if (remainedCapacity < count) {
            return false;
        }
        int current = this.getProductCount(product);
        if (current < -count)
            return false;
        products.put(product, current + count);
        remainedCapacity -= count;
        return true;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRemainedCapacity() {
        return remainedCapacity;
    }

    public String toString() {
        return "ProductPool :" +
                "\ncapacity = " +
                capacity +
                "\nremainedCapacity = " +
                remainedCapacity +
                "\nproducts = " +
                products;

    } // getInfo

    public void increaseCapacity(int increaseCapacity) {
        capacity += increaseCapacity;
        remainedCapacity += increaseCapacity;

    }
}
