package FarmFrenzy.GameUI;


import Transportation.Vehicle;
import Utils.ImageProperties;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

class VehicleView {
    private VehicleStatus status;
    private ImageProperties image;

    public VehicleView(VehicleStatus status, ImageProperties image) {
        this.status = status;
        this.image = image;
    }

    Group build(Vehicle vehicle) {
        Group group = new Group();
        group.getChildren().add(status.build(vehicle));
        ImageView imageView = image.toImageView(false);
        UIProperties.runEveryFrame(new Runnable() {
            int last = -1;

            @Override
            public void run() {
                if (last != vehicle.getLevel()) {
                    last = vehicle.getLevel();
                    String path = image.getImagePath() + String.format("0%d.png", last);
                    Image image = new Image(new File(path).toURI().toString());
                    imageView.setImage(image);
                }
            }
        });
        group.getChildren().add(imageView);
        return group;
    }
}
