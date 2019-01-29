package Utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class ImageProperties {
    private int positionX, positionY, width, height;
    private String image;
    private transient boolean isCenterPosition = false;

    public ImageProperties(int positionX, int positionY, int width, int height, String image) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    public ImageView toImageView() {
        if (isCenterPosition)
            calculatePosition();
        Image image = new Image(new File(this.image).toURI().toString());
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

    @Override
    public String toString() {
        return String.format("positionX = %d\npositionY = %d \nwidth = %d\nheight = %d\nimage = %s",
                positionX,
                positionY,
                width,
                height,
                image);
    }
}
