package FarmFrenzy.GameUI.MiddleMap;

import Animals.AnimalType;
import Animals.BaseAnimal;
import FarmFrenzy.GameUI.MiddleMap.Animals.AnimalView;
import Logic.Constants;
import Logic.MiddleMapObject;
import Products.GroundProduct;
import Products.Product;
import Utils.SpriteAnimation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.HashMap;

public class ImagePool {
    private HashMap<AnimalType, AnimalView> animalViews;
    private String productsPath;
    private transient HashMap<Product, Image> productViews;

    public ImagePool(HashMap<AnimalType, AnimalView> animalViews, String productsPath) {
        this.animalViews = animalViews;
        this.productsPath = productsPath;
    }

    SpriteAnimation getAnimalAnimation(BaseAnimal animal) {
        return animalViews.get(animal.getType()).getView(animal);
    }

    ImageView getProductView(MiddleMapObject object) {
        if (productViews == null)
            productViews = new HashMap<>();
        GroundProduct product = (GroundProduct) object;
        Image image = productViews.get(product.getType());
        if (image == null) {
            File file = new File(productsPath + product.getType().name() + "/normal.png");
            String path = file.toURI().toString();
            image = new Image(path);
            productViews.put(product.getType(), image);
        }
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(Constants.UI_PRODUCT_WIDTH);
        imageView.setFitHeight(Constants.UI_PRODUCT_HEIGHT);
        return imageView;
    }

    @Override
    public String toString() {
        return "ImagePool: animalViews: " + animalViews + "\nproductsPath: " + productsPath + "\n";
    }
}