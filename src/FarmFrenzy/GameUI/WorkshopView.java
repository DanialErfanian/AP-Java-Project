package FarmFrenzy.GameUI;

import Buildings.Workshop;
import Utils.AnimationProperties;
import Utils.ImageProperties;
import Utils.SpriteAnimation;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class WorkshopView {
    private ImageProperties background;
    private ImageProperties view;
    private AnimationProperties animationProperties;
    private transient SpriteAnimation animation;

    public WorkshopView(ImageProperties background, ImageProperties view, AnimationProperties animationProperties) {
        this.background = background;
        this.view = view;
        this.animationProperties = animationProperties;
    }

    public ImageProperties getBackground() {
        return background;
    }

    public ImageProperties getView() {
        return view;
    }

    public AnimationProperties getAnimationProperties() {
        return animationProperties;
    }

    private void update() {
        // TODO
    }

    private void startUpdater() {
        // TODO
        animation.play();
    }

    Group build(Workshop workshop) {
        Group group = new Group();
        group.getChildren().add(this.background.toImageView());
        ImageView imageView = this.view.toImageView();
        int count = animationProperties.getCount();
        int columns = animationProperties.getColumns();
        animation = new SpriteAnimation(imageView, Duration.millis(1000), count, columns);
        group.getChildren().add(imageView);
        startUpdater();
        group.getStyleClass().add("workshop");
        return group;
    }
}
