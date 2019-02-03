package FarmFrenzy.GameUI.MiddleMap.Animals;

import Utils.SpriteAnimation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.File;

public class AnimalStateView {
    private int count;
    private int columns;
    private String imagePath;
    private boolean isReversed;
    private transient Image image = null;

    public AnimalStateView(int count, int columns, String imagePath, boolean isReversed) {
        this.count = count;
        this.columns = columns;
        this.imagePath = imagePath;
        this.isReversed = isReversed;
    }

    SpriteAnimation getView() {
        if (image == null) {
            String path = new File(imagePath).toURI().toString();
            image = new Image(path);
        }
        ImageView imageView = new ImageView(image);
        if (isReversed)
            imageView.setScaleX(-1);
        SpriteAnimation animation = new SpriteAnimation(imageView, Duration.millis(1000), count, columns);
        animation.relax();
        animation.jumpTo(0);
        return animation;
    }

    @Override
    public String toString() {
        return "{" + count + ", " + columns + ", " + imagePath + ", " + isReversed + "}\n";
    }
}
