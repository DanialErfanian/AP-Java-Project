package FarmFrenzy.Multiplayer.HomePage;

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
            System.out.println(messageText);
            messagesBox.getChildren().add(new Label(messageText));
            messageField.clear();
        });
    }

    public void sendMessage() {
        sendImage.getOnMouseClicked().handle(null);
    }
}