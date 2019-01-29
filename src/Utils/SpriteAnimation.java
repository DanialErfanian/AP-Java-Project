package Utils;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteAnimation extends Transition {
    private final ImageView imageView;
    private int count;
    private int columns;
    private final int offsetX;
    private final int offsetY;
    private int width;
    private int height;

    private int lastIndex;

    public SpriteAnimation(ImageView imageView, Duration duration, int count, int columns) {
        this(imageView, duration);
        this.count = count;
        this.columns = columns;
    }

    public SpriteAnimation(ImageView imageView, Duration duration) {
        this.imageView = imageView;
        this.offsetX = 0;
        this.offsetY = 0;
        setCycleDuration(duration);
        setCycleCount(Animation.INDEFINITE);
        setInterpolator(Interpolator.LINEAR);
    }


    private void relax() {
        width = (int) (imageView.getImage().getWidth() / this.columns);
        height = (int) (imageView.getImage().getHeight() / Math.ceil(1. * count / columns));
    }

    @Override
    protected void interpolate(double k) {
        final int index = Math.min((int) Math.floor(k * count), count - 1);
        if (index != lastIndex) jumpTo(index);
    }

    private void jumpTo(int index) {
        index %= count;
        final int x = (index % columns) * width + offsetX;
        final int y = (index / columns) * height + offsetY;
        imageView.setViewport(new Rectangle2D(x, y, width, height));
        lastIndex = index;
    }

    public void update(AnimationProperties current) {
        if (imageView.getImage() == current.getImage())
            return;
        imageView.setVisible(false);

        this.imageView.setImage(current.getImage());
        this.columns = current.getColumns();
        this.count = current.getCount();

        relax();
        this.jumpTo(0);
        imageView.setVisible(true);
    }

    @Override
    public String toString() {
        return String.format("%s count: %d columns: %d imageView: %s", super.toString(), count, columns, imageView);
    }

    public void setImage(Image image) {
        imageView.setImage(image);
        relax();
    }
}
