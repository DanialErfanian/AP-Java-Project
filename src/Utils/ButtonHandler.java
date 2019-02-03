package Utils;

import FarmFrenzy.GameUI.UIProperties;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
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

    public boolean haveUpgrade() {
        return true;
    }

    public Group build(int labelSize) {
        Group group = new Group();
        ImageView imageView = properties.getImage().toImageView();

        int count = 4;
        int columns = 1;
        SpriteAnimation animation = new SpriteAnimation(imageView, Duration.ONE, count, columns);
        animation.relax();


        Label label = new Label(getText());
        label.setStyle("-fx-font-size: " + labelSize + "; -fx-text-alignment: center");
        label.setLayoutX(properties.getTextX());
        label.setLayoutY(properties.getTextY());
        startAnimationHandler(animation, label);
        group.getChildren().add(imageView);
        group.getChildren().add(label);
        UIProperties.runEveryFrame(() -> {
            if (!haveUpgrade())
                Platform.runLater(() -> group.getChildren().clear());
        });
        return group;
    }

    protected abstract String getText();

    private transient boolean done, enter, click;

    private void startAnimationHandler(SpriteAnimation animation, Label label) {
        config(animation.getImageView());
        config(label);
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

    private void config(Node node) {
        node.setOnMouseEntered(mouseEvent -> enter = true);
        node.setOnMousePressed(mouseEvent -> {
            click = true;
            done = false;
        });
        node.setOnMouseExited(mouseEvent -> enter = false);
        node.setOnMouseReleased(mouseDragEvent -> {
            click = false;
            done = false;
        });

    }
}
