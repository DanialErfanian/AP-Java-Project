package FarmFrenzy.GameUI;

import Buildings.Workshop;
import FarmFrenzy.GameUI.MiddleMap.ImagePool;
import FarmFrenzy.GameUI.MiddleMap.MiddleMapView;
import FarmFrenzy.Ui;
import Logic.Constants;
import Logic.Game;
import Utils.AnimationProperties;
import Utils.ImageProperties;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.ArrayList;


//TODO: add shadow file to vehicle and workshops (like src/Resources/Data/Game/Service/Car/01_m.png)
//TODO: convert this file to enum(from class)
//TODO: animal vaghti mikhad bokhore hamonja vaste targetesho avaz nakone
//TODO: when level is complete ??? :)
public class UIProperties {
    private final ImageProperties background, road;
    private final WorkshopView[] workshops = new WorkshopView[6];
    private final VehicleView helicopter, truck;
    private final MoneyStatus moneyStatus;
    private final ImageProperties warehouse;
    private final ImagePool imagePool;
    private final MiddleMapView middleMapView;
    private final WellView wellView;
    private static final ArrayList<Runnable> runnables = new ArrayList<>();
    private BuyAnimalView[] buyAnimalViews;

    public UIProperties(ImageProperties background, WorkshopView[] workshops, ImageProperties road, VehicleView helicopter, VehicleView truck, MoneyStatus moneyStatus, ImageProperties warehouse, ImagePool imagePool, MiddleMapView middleMapView, WellView wellView) {
        this.background = background;
        System.arraycopy(workshops, 0, this.workshops, 0, Math.min(6, workshops.length));
        this.road = road;
        this.helicopter = helicopter;
        this.truck = truck;
        this.moneyStatus = moneyStatus;
        this.warehouse = warehouse;
        this.imagePool = imagePool;
        this.middleMapView = middleMapView;
        this.wellView = wellView;
    }

    static UIProperties readFromFile(File file) {
        if (file == null)
            return null;
        try {
            String text = new String(Files.readAllBytes(file.toPath()));
            YaGson mapper = new YaGsonBuilder().setPrettyPrinting().create();
            UIProperties properties = mapper.fromJson(text, UIProperties.class);
            System.out.println("UI file: " + file.toString());
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean writeToFile(File file) {
        if (file == null)
            return false;
        YaGson mapper = new YaGsonBuilder().setPrettyPrinting().create();
        try {
            PrintStream result = new PrintStream(file);
            result.println(mapper.toJson(this, AnimationProperties.class));
            System.out.println("wrote to file: " + file);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Node buildBackground() {
        return background.toImageView();
    }

    Group build(Game game) {
        Group group = new Group();
        Pane pane = new Pane();
        group.getChildren().add(pane);

        pane.getChildren().add(buildBackground());
        pane.getChildren().add(buildWorkshops(game));
        pane.getChildren().add(buildWarehouse(game));
        pane.getChildren().add(buildRoad());
        pane.getChildren().add(truck.build(game.getTruck()));
        pane.getChildren().add(helicopter.build(game.getHelicopter()));
        pane.getChildren().add(moneyStatus.build(game));
        pane.getChildren().add(middleMapView.build(game, imagePool));
        pane.getChildren().add(wellView.build(game));
        for(BuyAnimalView buyAnimalView: buyAnimalViews)
            pane.getChildren().add(buyAnimalView.build(game));
        Thread thread = new Thread(() -> Ui.Run(game));
        thread.setDaemon(true);
        thread.start();

        thread = new Thread(() -> {
            while (true) {
                game.increaseTurn();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();


        startFrameUpdater();
        // TODO money and vehicles and...
//        group.setScaleX(2);
//        group.setScaleY(2);
//        group.setTranslateY(300);
//        group.setTranslateX(400);
        return group;
    }

    private void startFrameUpdater() {
        Thread thread = new Thread(() -> {
            while (true) {
                for (Runnable runnable : runnables)
                    runnable.run();
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

    private Group buildWarehouse(Game game) {
        Group group = new Group();
        ImageView imageView = warehouse.toImageView(false);
        runEveryFrame(new Runnable() {
            int last = -1;

            @Override
            public void run() {
                if (last != game.getWarehouse().getLevel()) {
                    last = game.getWarehouse().getLevel();
                    String path = warehouse.getImagePath() + String.format("0%d.png", last);
                    Image image = new Image(new File(path).toURI().toString());
                    imageView.setImage(image);
                }
            }
        });
        group.getChildren().add(imageView);
        return group;
    }

    private Node buildRoad() {
        return road.toImageView();
    }

    private Node buildWorkshops(Game game) {
        Group group = new Group();
        Workshop[] workshops = game.getWorkshops();
        for (int i = 0; i < Math.min(this.workshops.length, workshops.length); i++)
            group.getChildren().add(this.workshops[i].build(workshops[i]));
        return group;
    }

    public static void runEveryFrame(Runnable runnable) {
        runnables.add(runnable);
    }
}
