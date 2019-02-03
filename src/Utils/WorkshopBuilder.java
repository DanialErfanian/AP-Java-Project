package Utils;

import Products.Product;

import java.nio.file.Path;
import java.util.ArrayList;

public class WorkshopBuilder {
    private String name;
    private ArrayList<Product> inputs;
    private Product output;
    private Position position;
    private String animationFiles;

    public WorkshopBuilder(String name, ArrayList<Product> inputs, Product output, Position position, String animationFiles) {
        this.name = name;
        this.inputs = inputs;
        this.output = output;
        this.position = position;
        this.animationFiles = animationFiles;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Product> getInputs() {
        return inputs;
    }

    public Product getOutput() {
        return output;
    }

    public Position getPosition() {
        return position;
    }

    public String getAnimationFiles() {
        return animationFiles;
    }
}
