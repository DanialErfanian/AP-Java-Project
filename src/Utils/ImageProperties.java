package Utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class ImageProperties {
    private int positionX, positionY, width, height;
    private File image;
    private transient boolean isCenterPosition = false;

    public ImageProperties(int positionX, int positionY, int width, int height, File background) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
        this.image = background;
    }

    public ImageView toImageView(){
        if(isCenterPosition)
            calculatePosition();
        System.out.println(this.image.toURI());
        System.out.println("positionX = " + positionX);
        System.out.println("positionY = " + positionY);
        System.out.println("width = " + width);
        System.out.println("height = " + height);
        Image image = new Image(this.image.toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.relocate(positionX, positionY);
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        return imageView;
    }

    private void calculatePosition() {
        positionX -= width / 2;
        positionY -= height / 2;
        isCenterPosition = false;
    }
}
