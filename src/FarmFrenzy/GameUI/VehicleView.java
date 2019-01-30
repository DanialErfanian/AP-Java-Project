package FarmFrenzy.GameUI;

import Logic.Constants;
import Transportation.Vehicle;
import Utils.AnimationProperties;
import Utils.SpriteAnimation;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.File;

public class VehicleView {
    private transient boolean isCenterPosition = false;
    private AnimationProperties animation;
    private String imagePath;
    private int width;
    private int height;
    private int positionY;
    private int positionX1, positionX2;


    public VehicleView(AnimationProperties animation, String imagePath, int width, int height, int positionY, int positionX1, int positionX2) {
        this.animation = animation;
        this.imagePath = imagePath;
        this.width = width;
        this.height = height;
        this.positionY = positionY;
        this.positionX1 = positionX1;
        this.positionX2 = positionX2;
    }

    private void updateView(Vehicle vehicle, ImageView imageView) {// between 0 and 1
        double v = 1. * vehicle.getProgress() / vehicle.getFullProgress();
        imageView.setVisible(vehicle.getProgress() != 0);
        imageView.setScaleX((v > 0.5) ? +1 : -1);
        v = Math.min(v, 1 - v) * 2;
        int distance = positionX2 - positionX1;
        imageView.setX(positionX1 + distance * v);
    }

    Group build(Vehicle vehicle) {
        if (isCenterPosition) {
            positionX1 -= width / 2;
            positionX2 -= width / 2;
            positionY -= height / 2;
            isCenterPosition = false;
        }
        Group group = new Group();
        ImageView imageView = new ImageView();
        this.buildImageView(vehicle, imageView);
        group.getChildren().add(imageView);
        this.startAnimation(imageView);
        startUpdater(vehicle, imageView);
        return group;
    }

    private void startAnimation(ImageView imageView) {
        SpriteAnimation animation1;
        animation1 = new SpriteAnimation(imageView, Duration.millis(200), animation.getCount(), animation.getColumns());
        animation1.relax();
        animation1.play();
    }

    private void buildImageView(Vehicle vehicle, ImageView imageView) {
        String path = imagePath + String.format("0%d_mini.png", vehicle.getLevel());
        Image image = new Image(new File(path).toURI().toString());
        imageView.setImage(image);
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        imageView.setY(positionY);
    }

    private void startUpdater(Vehicle vehicle, ImageView imageView) {
        Thread thread = new Thread(new Runnable() {
            int lastLevel = -1;

            @Override
            public void run() {
                while (true) {
                    if (lastLevel != vehicle.getLevel()) {
                        buildImageView(vehicle, imageView);
                        lastLevel = vehicle.getLevel();
                    }
                    updateView(vehicle, imageView);
                    try {
                        Thread.sleep(1000 / Constants.UI_FPS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
