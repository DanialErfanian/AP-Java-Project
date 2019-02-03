package FarmFrenzy.Multiplayer;

import Utils.Network;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class HostNetConf implements Initializable {
    public Button joinButton;
    public Label serverIP;
    private String playerName;



    void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        serverIP.setWrapText(true);
        System.out.println(Network.getHostAddresses());
        serverIP.setText(Network.getHostAddresses());
    }
}
