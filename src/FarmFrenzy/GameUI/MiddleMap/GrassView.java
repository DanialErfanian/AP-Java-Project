package FarmFrenzy.GameUI.MiddleMap;

import FarmFrenzy.GameUI.UIProperties;
import Logic.Constants;
import Logic.Game;
import Utils.AnimationProperties;
import Utils.Position;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GrassView {
    private AnimationProperties animation;
    private String path;

    public GrassView(AnimationProperties animation, String path) {
        this.animation = animation;
        this.path = path;
    }

    public Group build(Game game, MiddleMapView middleMapView) {
        Group group = new Group();
        int mapHeight = game.getMap().getMapHeight();
        int mapWidth = game.getMap().getMapWidth();
        int X1 = middleMapView.getX1();
        int X2 = middleMapView.getX2();
        int Y1 = middleMapView.getY1();
        int Y2 = middleMapView.getY2();

        double grassWidth = 1. * (X2 - X1) / mapHeight * 2;
        double grassHeight = 1. * (Y2 - Y1) / mapWidth * 2;
        for (int x = 0; x < mapWidth; x++)
            for (int y = 0; y < mapHeight; y++) {
                int finalY = y;
                int finalX = x;
                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.GREEN);
                UIProperties.runEveryFrame(() -> {
                    double grass = game.getMap().getGrass(finalX, finalY);
                    double v = grass / Constants.GRASS_MAX_VALUE;
                    rectangle.setOpacity(v);
                });
                rectangle.setWidth(grassWidth);
                rectangle.setHeight(grassHeight);
                middleMapView.setPosition(rectangle, new Position(x, y), game);
                group.getChildren().add(rectangle);
            }
        return group;
    }
}
