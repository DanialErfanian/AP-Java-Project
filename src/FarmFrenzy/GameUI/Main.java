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

    public static Group build(String path) {
        Level level = Level.readFromFile(path);
        assert level != null;
        UIProperties properties = UIProperties.readFromFile(new File(level.getUIPropertiesPath()));
        Game game = new Game(level);
        return properties.build(game);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = build("src/Resources/levels/level1.json");
        primaryStage.setTitle("testing...");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}