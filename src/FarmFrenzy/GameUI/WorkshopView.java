package FarmFrenzy.GameUI;

import Buildings.Workshop;
import Logic.Constants;
import Utils.ButtonHandler;
import Utils.ButtonProperties;
import Utils.ImageProperties;
import Utils.SpriteAnimation;
import javafx.animation.Animation;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.File;

public class WorkshopView {
    private ImageProperties background;
    private int positionX, positionY;
    private ButtonProperties upgradeButton;

    public WorkshopView(ImageProperties background, int positionX, int positionY, ButtonProperties upgradeButton) {
        this.background = background;
        this.positionX = positionX;
        this.positionY = positionY;
        this.upgradeButton = upgradeButton;
    }

    public ImageProperties getBackground() {
        return background;
    }

    private void startUpdater(Workshop workshop, SpriteAnimation animation) {
        UIProperties.runEveryFrame(new Runnable() {
            int lastLevel = -1;

            @Override
            public void run() {
                int level = workshop.getLevel();
                if (lastLevel != level) {
                    lastLevel = level;
                    String path = workshop.getBuilder().getAnimationFiles();
                    path = path + String.format("0%d.png", level);
                    File file = new File(path);
                    animation.setImage(new Image(file.toURI().toString()));
                    animation.jumpTo(0);
                    animation.play();
                    ImageView imageView = animation.getImageView();
                    imageView.setFitWidth(Constants.WORKSHOP_VIEW_WIDTH);
                    imageView.setFitHeight(Constants.WORKSHOP_VIEW_HEIGHT);
                    imageView.setX(positionX - Constants.WORKSHOP_VIEW_WIDTH / 2.);
                    imageView.setY(positionY - Constants.WORKSHOP_VIEW_HEIGHT / 2.);
                    return;
                }
                if (workshop.isRunning()) {
                    if (animation.getStatus() == Animation.Status.STOPPED)
                        animation.play();
                } else {
                    animation.stop();
                    animation.jumpTo(0);
                }
            }
        });
        animation.getImageView().setOnMouseClicked(mouseEvent -> workshop.start());
    }

    Group build(Workshop workshop) {
        Group group = new Group();
        if (workshop == null)
            return group;
        group.getChildren().add(this.background.toImageView());
        ImageView imageView = new ImageView();
        int count = Constants.WORKSHOP_VIEW_ANIMATION_COUNT;
        int columns = Constants.WORKSHOP_VIEW_ANIMATION_COLUMNS;
        SpriteAnimation animation = new SpriteAnimation(imageView, Duration.millis(1000), count, columns);
        group.getChildren().add(imageView);
        startUpdater(workshop, animation);
        if (upgradeButton == null)
            return group;
        ButtonHandler upgrade = new ButtonHandler(upgradeButton) {
            @Override
            protected boolean isAvailable() {
                return workshop.getGame().getMoney() >= workshop.getUpgradeCost();
            }

            @Override
            public void onClick() {
                workshop.upgrade();
            }

            @Override
            protected String getText() {
                return Integer.toString(workshop.getUpgradeCost());
            }

            @Override
            public boolean haveUpgrade() {
                return workshop.haveUpgrade();
            }
        };
        group.getChildren().add(upgrade.build(15));
        return group;
    }
}
