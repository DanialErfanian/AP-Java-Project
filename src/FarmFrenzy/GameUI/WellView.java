package FarmFrenzy.GameUI;

import Logic.Game;
import Utils.AnimationProperties;
import Utils.SpriteAnimation;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.File;


public class WellView {
    private AnimationProperties animationProperties;
    private int x, y, width, height;
    private String imagesPath;
    private transient int lastLevel = -1;
    private transient SpriteAnimation animation;

    public WellView(AnimationProperties animation, int x, int y, int width, int height, String imagesPath) {
        this.animationProperties = animation;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.imagesPath = imagesPath;
    }

    public Group build(Game game) {
        Group group = new Group();
        ImageView imageView = new ImageView();
        group.getChildren().add(imageView);
        int count = animationProperties.getCount();
        int columns = animationProperties.getColumns();
        animation = new SpriteAnimation(imageView, Duration.millis(1000), count, columns);
        animation.setCycleCount(3);
        animation.setOnFinished(actionEvent -> game.well());
        updateLevel(game, imageView);
        Rectangle rectangle = new Rectangle();
        rectangle.setX(x - 30);
        rectangle.setY(y - 15);
        rectangle.setWidth(80);
        rectangle.setHeight(60);
        rectangle.setOpacity(0);
        x -= width / 2;
        y -= height / 2;
        rectangle.setOnMouseClicked(mouseEvent -> animation.play());
        group.getChildren().add(rectangle);

        UIProperties.runEveryFrame(() -> updateLevel(game, imageView));
        imageView.setX(x);
        imageView.setY(y);
        return group;
    }

    private void updateLevel(Game game, ImageView imageView) {
        int level = game.getMap().getWell().getLevel();
        if (lastLevel == level)
            return;
        lastLevel = level;
        String path = new File(imagesPath + "0" + level + ".png").toURI().toString();
        System.out.println(path);
        Image image = new Image(path);
        imageView.setImage(image);
        animation.relax();
        animation.jumpTo(0);
    }
}
