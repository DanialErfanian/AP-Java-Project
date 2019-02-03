package FarmFrenzy.Multiplayer;

import Server.Server;
import Utils.Network;
import Utils.NetworkConfig;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HostNetConf implements Initializable {
    public Button startButton;
    public Label serverIP;
    public TextField serverPort;
    private String playerName;// TODO why not used? :thinking:


    void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startButton.setOnMouseClicked(mouseEvent -> {
            Server server = Server.getInstance();
            int port = Integer.parseInt(serverPort.getText());//TODO exceptions?
            server.setNetConf(new NetworkConfig(null, port));

            Thread thread = new Thread(server);
            thread.setDaemon(true);
            thread.start();
            Scene scene = startButton.getScene();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage/View.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            scene.setRoot(root);
        });
        serverIP.setWrapText(true);
        serverIP.setText(Network.getHostAddresses());
    }
}
