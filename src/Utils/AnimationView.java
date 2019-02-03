package Utils;

import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class AnimationView {
    private final AnimationProperties animationProperties;
    private final ImageProperties imageProperties;

    public AnimationView(AnimationProperties animationProperties, ImageProperties imageProperties) {
        this.animationProperties = animationProperties;
        this.imageProperties = imageProperties;
    }

    public SpriteAnimation build() {
        ImageView imageView = imageProperties.toImageView();
        SpriteAnimation animation;
        animation = new SpriteAnimation(imageView, Duration.millis(1000), animationProperties.getCount(), animationProperties.getColumns());
        animation.relax();
        animation.jumpTo(0);
        return animation;
    }
}
