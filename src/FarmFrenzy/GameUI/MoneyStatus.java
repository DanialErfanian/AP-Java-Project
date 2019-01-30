package FarmFrenzy.GameUI;

import Logic.Constants;
import Logic.Game;
import Utils.AnimationView;
import Utils.SpriteAnimation;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;

class MoneyStatus {
    private AnimationView coin;

    public MoneyStatus(AnimationView coin) {
        this.coin = coin;
    }

    Group build(Game game) {
        Group group = new Group();
        SpriteAnimation animation = coin.build();
        ImageView imageView = animation.getImageView();
        animation.play();
        group.getChildren().add(imageView);

        Label label = new Label();
        label.setStyle("-fx-font-weight: BOLD; -fx-text-fill: #ffd400");
        label.setTextAlignment(TextAlignment.CENTER);
        label.setTranslateX(imageView.getX());
        label.setTranslateY(imageView.getY() + 35);
        this.startUpdater(game, label);
        group.getChildren().add(label);
        return group;

    }

    private void startUpdater(Game game, Label label) {
        Thread thread = new Thread(() -> {
            while (true) {
                Platform.runLater(() -> label.setText(Integer.toString(game.getMoney())));
                try {
                    Thread.sleep(1000 / Constants.UI_FPS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
