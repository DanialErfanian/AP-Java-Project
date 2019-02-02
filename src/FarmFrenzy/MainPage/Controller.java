package FarmFrenzy.MainPage;

import FarmFrenzy.GameUI.Main;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public ImageView imageView;
    public Button playButton;
    public Button exitButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playButton.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            Scene scene = stage.getScene();
            scene.setRoot(Main.build("media/Level-testing.json"));
        });

        exitButton.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
        });
    }
}
