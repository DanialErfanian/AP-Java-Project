package FarmFrenzy.Multiplayer.HomePage;

import Server.Client;
import Server.Exceptions.BadServerException;
import Server.Exceptions.StatusCodeException;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public ImageView sendImage;
    public TextField messageField;
    public VBox messagesBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
            Platform.runLater(() -> messagesBox.getChildren().add(label));
        });
    }

    public void sendMessage() {
        sendImage.getOnMouseClicked().handle(null);
    }
}