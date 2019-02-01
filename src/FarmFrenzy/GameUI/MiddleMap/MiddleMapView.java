package FarmFrenzy.GameUI.MiddleMap;

import Animals.AnimalState;
import Animals.BaseAnimal;
import Logic.Constants;
import Logic.Game;
import Logic.MiddleMapObject;
import Products.GroundProduct;
import Utils.Position;
import Utils.SpriteAnimation;
import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class MiddleMapView {
    private int X1, X2, Y1, Y2;

    public MiddleMapView(int x1, int x2, int y1, int y2) {
        X1 = x1;
        X2 = x2;
        Y1 = y1;
        Y2 = y2;
    }

    public Group build(Game game, ImagePool imagePool) {
        Group group = new Group();
        Thread thread = new Thread(() -> {
            while (true) {
                update(group, game, imagePool);
                try {
                    Thread.sleep(1000 / Constants.UI_FPS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        return group;
    }

    private void setPosition(ImageView imageView, MiddleMapObject object) {
        Position position = object.getPosition();
        if (position == null)

            return;
        int mapWidth = object.getGame().getMap().getMapWidth();
        int mapHeight = object.getGame().getMap().getMapHeight();
        double x = 1. * (X2 - X1) * position.getX() / mapWidth + X1;
        double y = 1. * (Y2 - Y1) * position.getY() / mapHeight + Y1;
        assert imageView != null;
        Platform.runLater(() -> {
            imageView.setX(x);
            imageView.setY(y);
        });
    }

    private void update(Group group, Game game, ImagePool imagePool) {
        for (MiddleMapObject object : game.getMap().getObjects())
            if (object != null && object.isValid()) {
                if (object.isAddedRecently()) {
                    buildView(group, object, imagePool);
                    object.setAddedRecently(false);
                }
            }
    }

    private void buildView(Group group, MiddleMapObject object, ImagePool imagePool) {
        if (object instanceof BaseAnimal)
            buildAnimalView(group, (BaseAnimal) object, imagePool);
        else
            buildProductView(group, (GroundProduct) object, imagePool);
    }

    private void buildProductView(Group group, GroundProduct object, ImagePool imagePool) {
        ImageView imageView = imagePool.getProductView(object);
        Platform.runLater(() -> group.getChildren().add(imageView));
        Thread thread = new Thread(() -> {
            while (true) {
                if (object.getAmount() == 0) {
                    imageView.setImage(null);
                    imageView.setVisible(false);
                    return;
                }
                setPosition(imageView, object);
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

    private void buildAnimalView(Group group, BaseAnimal animal, ImagePool imagePool) {
        ImageView imageView = new ImageView();
        Platform.runLater(() -> group.getChildren().add(imageView));
        Thread thread = new Thread(new Runnable() {
            AnimalState lastState = null;
            SpriteAnimation animation = null;

            @Override
            public void run() {
                while (true) {
                    if (animation != null && animation.getStatus() == Animation.Status.STOPPED) {
                        if (animation != null) animation.stop();
                        imageView.setVisible(false);
                        return;
                    }
                    if (animal.getState() != lastState) {
                        if (animation != null) animation.stop();
                        lastState = animal.getState();
                        animation = imagePool.getAnimalAnimation(animal);
                        if (animation == null) {
                            System.out.println(animal.getState());
                            System.out.println(animal);
                            break;
                        }
                        if (!animal.isValid())
                            animation.stop();
                        if (lastState == AnimalState.death)
                            animation.setCycleCount(1);
                        animation.swapImageView(imageView);
                        animation.play();
                    }
                    setPosition(imageView, animal);
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
