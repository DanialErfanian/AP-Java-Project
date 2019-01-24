package FarmFrenzy.MainPage;

import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public ImageView imageView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageView.setOnMouseClicked(mouseEvent -> {
            System.out.println("salam");
            System.out.println(imageView.getFitWidth());
            System.out.println(imageView.getFitHeight());
            double x;
            double y;
            x = imageView.getFitWidth();
            y = imageView.getFitHeight();
            imageView.setFitWidth(x + 10);
            imageView.setFitHeight(y + 10);

        });
    }
}
