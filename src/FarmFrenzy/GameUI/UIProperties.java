package FarmFrenzy.GameUI;

import Buildings.Workshop;
import Logic.Game;
import Utils.AnimationProperties;
import Utils.ImageProperties;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;
import javafx.scene.Group;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;

public class UIProperties {
    private final ImageProperties background;
    private final WorkshopView[] workshops = new WorkshopView[6];


    public UIProperties(ImageProperties background, WorkshopView[] workshops) {
        this.background = background;
        System.arraycopy(workshops, 0, this.workshops, 0, Math.min(6, workshops.length));
    }

    static UIProperties readFromFile(File file) {
        if (file == null)
            return null;
        try {
            String text = new String(Files.readAllBytes(file.toPath()));
            YaGson mapper = new YaGsonBuilder().setPrettyPrinting().create();
            UIProperties properties = mapper.fromJson(text, UIProperties.class);
            System.out.println("UI file: " + file.toString());
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean writeToFile(File file) {
        if (file == null)
            return false;
        YaGson mapper = new YaGsonBuilder().setPrettyPrinting().create();
        try {
            PrintStream result = new PrintStream(file);
            result.println(mapper.toJson(this, AnimationProperties.class));
            System.out.println("wrote to in file: " + file);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    Group build(Game game) {
        Workshop[] workshops = game.getWorkshops();
        Group group = new Group();
        Pane pane = new Pane();
        group.getChildren().add(pane);

        pane.getChildren().add(background.toImageView());

        for (int i = 0; i < Math.min(this.workshops.length, workshops.length); i++)
            pane.getChildren().add(this.workshops[i].build(workshops[i]));
        // TODO money and vehicles and...
        return group;
    }
}
