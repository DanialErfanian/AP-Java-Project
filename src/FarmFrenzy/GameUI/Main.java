package FarmFrenzy.GameUI;

import Buildings.Workshop;
import Logic.Game;
import Products.Product;
import Products.WorkshopBuilder;
import Utils.Position;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Game game = new Game();
        Workshop workshop = new Workshop(game, new WorkshopBuilder("some name", new ArrayList<>(), Product.BEAR, new Position(0, 0)));
        Workshop[] workshops = new Workshop[6];
        for (int i = 0; i < 6; i++)
            workshops[i] = workshop;

        UIProperties properties = UIProperties.readFromFile(new File("src/Resources/Data/Game/Back/1.json"));

        Group root = properties.build(workshops);
        primaryStage.setTitle("testing...");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}