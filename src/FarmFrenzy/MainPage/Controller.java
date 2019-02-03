package FarmFrenzy.MainPage;

import FarmFrenzy.GameUI.Main;
import FarmFrenzy.Multiplayer.HostClient;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public ImageView imageView;
    public Button playButton;
    public Button exitButton;
    public Button multiplayer;
    public TextField nameField;

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

        multiplayer.setOnMouseClicked(mouseEvent -> {
            Scene scene = multiplayer.getScene();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Multiplayer/HostClient.fxml"));
            try {
                Parent parent = loader.load();
                HostClient controller = loader.getController();
                controller.setPlayerName(nameField.getText());
                scene.setRoot(parent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
