package FarmFrenzy.GameUI.MiddleMap.Animals;

import Animals.AnimalState;
import Animals.BaseAnimal;
import FarmFrenzy.GameUI.MiddleMap.ImagePool;
import FarmFrenzy.GameUI.MiddleMap.MiddleMapView;
import Utils.SpriteAnimation;
import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

import java.util.HashMap;

public class AnimalController {
    private BaseAnimal animal;
    private ImagePool imagePool;
    private Group group;
    private HashMap<AnimalState, SpriteAnimation> animations = new HashMap<>();
    private MiddleMapView middleMapView;
    private transient ImageView imageView;

    public AnimalController(BaseAnimal animal, ImagePool imagePool) {
        this.animal = animal;
        this.imagePool = imagePool;
    }

    public Group build(MiddleMapView middleMapView) {
        group = new Group();
        this.middleMapView = middleMapView;
        return group;
    }

    public void update() {
        AnimalState state = animal.getState();
        SpriteAnimation animation = animations.get(state);
        // TODO death state position don't fixed
        // try to build single animation for an animal
        // and change it's image and properties to update view
        if (state == null)
            return;
        if (animation == null) {
            animation = imagePool.getAnimalAnimation(animal);
            if (state == AnimalState.death)
                animation.setCycleCount(1);
            animation.play();
            animations.put(state, animation);
        }
        Platform.runLater(() -> group.getChildren().clear());
        if (animation.getStatus() != Animation.Status.STOPPED) {
            ImageView imageView = animation.getImageView();
            if (state == AnimalState.death) {
                imageView.setX(this.imageView.getX());
                imageView.setY(this.imageView.getY());
            } else
                middleMapView.setPosition(imageView, animal);
            this.imageView = imageView;
            Platform.runLater(() -> group.getChildren().add(imageView));
        }
    }
}
