package FarmFrenzy.GameUI;


import Logic.Constants;
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
        Thread thread = new Thread(new Runnable() {
            int last = -1;

            @Override
            public void run() {
                while (true) {
                    if (last != vehicle.getLevel()) {
                        last = vehicle.getLevel();
                        String path = image.getImagePath() + String.format("0%d.png", last);
                        Image image = new Image(new File(path).toURI().toString());
                        imageView.setImage(image);
                    }
                    try {
                        Thread.sleep(1000 / Constants.UI_FPS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        group.getChildren().add(imageView);
        thread.setDaemon(true);
        thread.start();
        return group;
    }
}
