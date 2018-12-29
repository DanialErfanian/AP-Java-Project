package Products;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ProductPool implements java.io.Serializable {
    private int capacity;
    private int remainedCapacity;
    private HashMap<Product, Integer> products = new HashMap<>();
    private int allocatedCapacity = 0;

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

    public void clear() {
        products.clear();
        remainedCapacity = capacity;
    }

    public Set<Map.Entry<Product, Integer>> getEntrySet() {
        return products.entrySet();
    }

    public boolean allocate(int count) {
        if (remainedCapacity < count)
            return false;
        allocatedCapacity += count;
        remainedCapacity -= count;
        return true;
    }

    public int getFilledCapacity() {
        return this.capacity - this.remainedCapacity;
    }

    public boolean unallocate(int count) {
        if(allocatedCapacity < count)
            return false;
        allocatedCapacity -= count;
        remainedCapacity += count;
        return true;
    }
}
