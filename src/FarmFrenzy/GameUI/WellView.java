package FarmFrenzy.GameUI;

import Buildings.Well;
import Logic.Game;
import Utils.AnimationProperties;
import Utils.ButtonHandler;
import Utils.ButtonProperties;
import Utils.SpriteAnimation;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.File;


public class WellView {
    private AnimationProperties animationProperties;
    private int x, y, width, height;
    private String imagesPath;
    private ButtonProperties upgradeButton;

    private transient int lastLevel = -1;
    private transient SpriteAnimation animation;

    public WellView(AnimationProperties animationProperties, int x, int y, int width, int height, String imagesPath, ButtonProperties upgradeButton) {
        this.animationProperties = animationProperties;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.imagesPath = imagesPath;
        this.upgradeButton = upgradeButton;
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

        addUpgradeButton(group, game);

        return group;
    }

    private void addUpgradeButton(Group group, Game game) {
        ButtonHandler upgrade = new ButtonHandler(upgradeButton) {
            Well well = game.getMap().getWell();

            @Override
            protected boolean isAvailable() {
                return game.getMoney() >= well.getUpgradeCost();
            }

            @Override
            public void onClick() {
                well.upgrade();
            }

            @Override
            public boolean haveUpgrade() {
                return well.haveUpgrade();
            }

            @Override
            protected String getText() {
                return Integer.toString(well.getUpgradeCost());
            }
        };
        group.getChildren().add(upgrade.build(15));
    }

    private void updateLevel(Game game, ImageView imageView) {
        int level = game.getMap().getWell().getLevel();
        if (lastLevel == level)
            return;
        lastLevel = level;
        String path = new File(imagesPath + "0" + level + ".png").toURI().toString();
        Image image = new Image(path);
        imageView.setImage(image);
        animation.relax();
        animation.jumpTo(0);
    }
}
