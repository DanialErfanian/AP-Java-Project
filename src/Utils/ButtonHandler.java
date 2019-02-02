package Utils;

import FarmFrenzy.GameUI.UIProperties;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public abstract class ButtonHandler {

    private final ButtonProperties properties;

    protected ButtonHandler(ButtonProperties properties) {
        this.properties = properties;
    }

    protected abstract boolean isAvailable();

    public abstract void onClick();

    public Group build() {
        Group group = new Group();
        ImageView imageView = properties.getImage().toImageView();

        int count = 4;
        int columns = 1;
        SpriteAnimation animation = new SpriteAnimation(imageView, Duration.ONE, count, columns);
        animation.relax();

        startAnimationHandler(animation);

        Label label = new Label(getText());
        label.setStyle("-fx-font-size: 10; -fx-text-alignment: center");
        label.setLayoutX(properties.getTextX());
        label.setLayoutY(properties.getTextY());
        group.getChildren().add(imageView);
        group.getChildren().add(label);
        return group;
    }

    protected abstract String getText();

    private transient boolean done, enter, click;

    private void startAnimationHandler(SpriteAnimation animation) {
        ImageView imageView = animation.getImageView();
        imageView.setOnMouseEntered(mouseEvent -> enter = true);
        imageView.setOnMousePressed(mouseEvent -> {
            click = true;
            done = false;
        });
        imageView.setOnMouseExited(mouseEvent -> enter = false);
        imageView.setOnMouseReleased(mouseDragEvent -> {
            click = false;
            done = false;
        });
        UIProperties.runEveryFrame(() -> {
            if (!isAvailable())
                animation.jumpTo(3);
            else if (click) {
                animation.jumpTo(2);
                if (!done) {
                    onClick();
                    done = true;
                }
            } else if (enter)
                animation.jumpTo(1);
            else
                animation.jumpTo(0);
        });
    }
}
