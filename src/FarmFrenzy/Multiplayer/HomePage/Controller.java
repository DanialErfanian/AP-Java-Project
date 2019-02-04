package FarmFrenzy.Multiplayer.HomePage;

import Server.Client;
import Server.Exceptions.BadServerException;
import Server.Exceptions.StatusCodeException;
import Server.User.ScoreboardProfile;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

//TODO update usersBox
//TODO mese sag CPU migire fek konam hamintori thread misaze
//TODO send leave request on client close request
//TODO send server playerName to for join to game :)

public class Controller implements Initializable {
    public ImageView sendImage;
    public TextField messageField;
    public VBox messagesBox;
    public VBox usersBox;
    public ScrollPane scrollPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateScoreboard();
        sendImage.setOnMouseClicked(mouseEvent -> {
            String messageText = messageField.getText();
            messageField.clear();
            try {
                Client.getInstance().sendMessage(messageText);
            } catch (BadServerException | StatusCodeException e) {
                e.printStackTrace();
            }
        });
        Client.getInstance().setOnNewMessage(message -> {
            Label label = new Label(message.getCompleteText());
            Platform.runLater(() -> {
                messagesBox.getChildren().add(label);
                scrollPane.setVvalue(1);
            });
        });
        Client.getInstance().setOnScoreboardChange(this::updateScoreboard);
    }

    private void updateScoreboard() {
        Platform.runLater(() -> usersBox.getChildren().clear());
        try {
            ArrayList<ScoreboardProfile> profiles = Client.getInstance().getScoreboardProfiles();
            for (ScoreboardProfile profile : profiles)
                Platform.runLater(() -> usersBox.getChildren().add(new Label(profile.getName())));
        } catch (BadServerException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage() {
        sendImage.getOnMouseClicked().handle(null);
    }

    public void update() {
        Scene scene = usersBox.getScene();
        scene.getWindow().setOnCloseRequest(windowEvent -> {
            try {
                Client.getInstance().leaveServer();
            } catch (BadServerException | StatusCodeException e) {
                e.printStackTrace();
            }
            System.exit(0);
        });
    }
}