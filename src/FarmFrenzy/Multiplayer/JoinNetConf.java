package FarmFrenzy.Multiplayer;

import Server.Client;
import Server.Communication.Handlers.CommandSender;
import Server.Exceptions.BadServerException;
import Server.Exceptions.StatusCodeException;
import Server.User.HostProfile;
import Utils.NetworkConfig;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
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
        joinButton.setOnMouseClicked(mouseEvent -> {
            int sport = Integer.parseInt(serverPort.getText());//TODO exceptions?
            int lport = Integer.parseInt(listenPort.getText());//TODO exceptions?

            String IP = serverIP.getText(); // TODO validate IP
            Client client = Client.getInstance();
            client.setProfile(new HostProfile(username.getText(), playerName, new NetworkConfig(null, lport)));
            client.setCommandSender(new CommandSender(new NetworkConfig(IP, sport)));
            client.setClientNetConf(new NetworkConfig(null, lport));
            try {
                client.joinServer();
            } catch (BadServerException | StatusCodeException e) {
                e.printStackTrace();
            }
            client.runUpdateReceiver();

            // Change Page
            Scene scene = joinButton.getScene();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage/ClientHomeView.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            scene.setRoot(root);
        });
    }
}
