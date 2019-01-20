package Animals.AnimalView;

import Utils.SpriteAnimation;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class AnimalViewController implements Initializable {
    private StateHandler handler;
    private SpriteAnimation animation;
    private boolean up, down, right, left;
    private final String defaultState = "eat";

    @FXML
    public ImageView imageView;
    public Pane rootPane;

    private AnimationTimer timer = new AnimationTimer() {
        private long lastUpdate = 0;

        @Override
        public void handle(long now) {
            if (now - lastUpdate >= 100_000_000) {
                updatePosition();
                updateDirection();
                lastUpdate = now;
            }
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        final String resourcePath = "/home/danial/Desktop/AP/src/resources/Animals/Africa/GuineaFowl/";
        handler = new StateHandler(resourcePath);
        animation = new SpriteAnimation(imageView, Duration.millis(1000));
        setState(defaultState);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
        timer.start();
    }


    private void setState(String state) {
        handler.setState(state);
        animation.update(handler.getCurrent());
    }

    private void updateDirection() {
        imageView.setScaleX(1);
        if (up && !down && !left && !right)// up
            setState("up");
        else if (!up && down && !left && !right)// down
            setState("down");
        else if (!up && !down && left && !right)// left
            setState("left");
        else if (!up && !down && !left && right) {// right
            setState("left");
            imageView.setScaleX(-1);
        } else if (up && !down && left && !right)// up left
            setState("up_left");
        else if (!up && down && left && !right)// down left
            setState("down_left");
        else if (up && !down && !left) {// up right  note: right is always true
            setState("up_left");
            imageView.setScaleX(-1);
        } else if (!up && down && !left) {// down right  note: right is always true
            setState("down_left");
            imageView.setScaleX(-1);
        } else
            setState(defaultState);
    }

    private void updatePosition() {
        int dx = 0, dy = 0;
        final int moveUnit = 10;
        if (up) dy -= moveUnit;
        if (down) dy += moveUnit;
        if (right) dx += moveUnit;
        if (left) dx -= moveUnit;
        moveImageBy(dx, dy);
    }

    private void moveImageBy(int dx, int dy) {
        if (dx == 0 && dy == 0) return;
        double x = imageView.getX() + dx;
        double y = imageView.getY() + dy;

        moveImageTo(x, y);
    }


    private void moveImageTo(double x, double y) {
        imageView.setX(x);
        imageView.setY(y);
    }


    void handleKeyPress(KeyEvent keyEvent) {
        handleKey(keyEvent, true);
    }

    void handleKeyReleased(KeyEvent keyEvent) {
        handleKey(keyEvent, false);
    }

    private void handleKey(KeyEvent event, boolean press) {
        switch (event.getCode()) {
            case UP:
                up = press;
                break;
            case DOWN:
                down = press;
                break;
            case LEFT:
                left = press;
                break;
            case RIGHT:
                right = press;
                break;
        }
    }
}
