package FarmFrenzy.Multiplayer;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HostClient implements Initializable {
    public Button hostButton;
    public Button joinButton;
    private String playerName;

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hostButton.setOnMouseClicked(mouseEvent -> {
            Scene scene = hostButton.getScene();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HostNetConf.fxml"));
            try {
                Parent parent = loader.load();
                HostNetConf controller = loader.getController();
                controller.setPlayerName(playerName);
                scene.setRoot(parent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        joinButton.setOnMouseClicked(mouseEvent -> {
            Scene scene = hostButton.getScene();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("JoinNetConf.fxml"));
            try {
                Parent parent = loader.load();
                JoinNetConf controller = loader.getController();
                controller.setPlayerName(playerName);
                scene.setRoot(parent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
