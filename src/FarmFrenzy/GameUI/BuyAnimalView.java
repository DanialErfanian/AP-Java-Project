package FarmFrenzy.GameUI;

import Animals.AnimalType;
import Logic.Game;
import Utils.AnimationProperties;
import Utils.ImageProperties;
import Utils.SpriteAnimation;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class BuyAnimalView {
    private AnimalType type;
    private ImageProperties image;
    private AnimationProperties animation;
    private int textX, textY, textWidth, textHeight;

    public BuyAnimalView(AnimalType type, ImageProperties image, int textX, int textY, int textWidth, int textHeight) {
        this.type = type;
        this.image = image;
        this.textX = textX;
        this.textY = textY;
        this.textWidth = textWidth;
        this.textHeight = textHeight;
    }

    public Group build(Game game) {
        Group group = new Group();
        ImageView imageView = image.toImageView();

        group.getChildren().add(imageView);

        int count = this.animation.getCount();
        int columns = this.animation.getColumns();
        SpriteAnimation animation = new SpriteAnimation(imageView, Duration.ONE, count, columns);
        animation.relax();

        startAnimationHandler(game, animation);

        Label label = new Label(game.getAnimalBuyCost(type) + "");
        label.setStyle("-fx-font-size: 10; -fx-text-alignment: center");
        label.setLayoutX(textX);
        label.setLayoutY(textY);

        group.getChildren().add(label);
        return group;
    }

    private transient boolean click, enter, done;

    private void startAnimationHandler(Game game, SpriteAnimation animation) {
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
            if (game.getMoney() < game.getAnimalBuyCost(type))
                animation.jumpTo(3);
            else if (click) {
                animation.jumpTo(2);
                if (!done) {
                    game.buy(type);
                    done = true;
                }
            } else if (enter)
                animation.jumpTo(1);
            else
                animation.jumpTo(0);
        });
    }
}
