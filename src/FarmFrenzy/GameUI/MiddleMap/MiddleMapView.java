package FarmFrenzy.GameUI.MiddleMap;

import Animals.BaseAnimal;
import FarmFrenzy.GameUI.MiddleMap.Animals.AnimalController;
import FarmFrenzy.GameUI.UIProperties;
import Logic.Game;
import Logic.MiddleMapObject;
import Products.GroundProduct;
import Utils.Position;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class MiddleMapView {
    private int X1, X2, Y1, Y2;
    private transient ArrayList<AnimalController> controllers;

    public MiddleMapView(int x1, int x2, int y1, int y2) {
        X1 = x1;
        X2 = x2;
        Y1 = y1;
        Y2 = y2;
    }

    public Group build(Game game, ImagePool imagePool) {
        this.controllers = new ArrayList<>();
        Group group = new Group();
        group.getChildren().add(handleProducts(game, imagePool));
        UIProperties.runEveryFrame(() -> update(group, game, imagePool));
        UIProperties.runEveryFrame((() -> {
            for (AnimalController controller : controllers)
                controller.update();
        }));
        return group;
    }

    private Group handleProducts(Game game, ImagePool imagePool) {
        Group group = new Group();
        UIProperties.runEveryFrame(() -> {
            Platform.runLater(() -> group.getChildren().clear());
            ArrayList<MiddleMapObject> objects = game.getMap().getObjects();
            for (MiddleMapObject object : objects)
                if (object instanceof GroundProduct) {
                    ImageView imageView = imagePool.getProductView((GroundProduct) object);
                    setPosition(imageView, object);
                    Platform.runLater(() -> group.getChildren().add(imageView));
                }
        });
        return group;
    }

    public void setPosition(ImageView imageView, MiddleMapObject object) {
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
            if (object instanceof BaseAnimal && object.isAddedRecently()) {
                controllers.add(buildAnimalView(group, (BaseAnimal) object, imagePool));
                object.setAddedRecently(false);
            }
    }

    private AnimalController buildAnimalView(Group group, BaseAnimal animal, ImagePool imagePool) {
        AnimalController controller = new AnimalController(animal, imagePool);
        Platform.runLater(() -> group.getChildren().add(controller.build(this)));
        return controller;
    }
}
