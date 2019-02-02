package FarmFrenzy.GameUI;

import Buildings.Workshop;
import Logic.Constants;
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

    public WorkshopView(ImageProperties background, int positionX, int positionY) {
        this.background = background;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public ImageProperties getBackground() {
        return background;
    }


    private void update() {
        // TODO
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
                    animation.relax();
                    animation.jumpTo(0);
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
        imageView.setFitWidth(Constants.WORKSHOP_VIEW_WIDTH);
        imageView.setFitHeight(Constants.WORKSHOP_VIEW_HEIGHT);
        imageView.setX(this.positionX - imageView.getFitWidth() / 2);
        imageView.setY(this.positionY - imageView.getFitHeight() / 2);
        startUpdater(workshop, animation);
        return group;
    }
}
