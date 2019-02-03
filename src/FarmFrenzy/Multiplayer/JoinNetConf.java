package FarmFrenzy.Multiplayer;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class JoinNetConf implements Initializable {
    public Button joinButton;
    public TextField username;
    public TextField listenPort;
    public TextField serverIP;
    public TextField serverPort;
    private String playerName;

    void setPlayerName(String playerName) {
        this.playerName = playerName;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        NetworkConfig config = new NetworkConfig(serverIP)
//        HostProfile hostProfile = new HostProfile(username, playerName, )
    }
}
