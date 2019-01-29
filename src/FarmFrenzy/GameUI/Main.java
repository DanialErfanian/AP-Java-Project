package FarmFrenzy.GameUI;

import Logic.Game;
import Logic.Level;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Level level = Level.readFromFile("media/Level-testing.json");
        assert level != null;
        UIProperties properties = UIProperties.readFromFile(new File(level.getUIPropertiesPath()));

        Game game = new Game(level);
        Group root = properties.build(game);
        primaryStage.setTitle("testing...");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}