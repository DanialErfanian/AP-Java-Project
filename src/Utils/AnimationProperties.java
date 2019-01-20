package Utils;

import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;
import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AnimationProperties {
    private final int count;
    private final int columns;
    private transient Image image;

    public AnimationProperties(int count, int columns) {
        this.count = count;
        this.columns = columns;
    }

    public static AnimationProperties readFromFile(String path) {
        if (path == null)
            return null;
        try {
            String text = new String(Files.readAllBytes(Paths.get(path + ".json")));
            YaGson mapper = new YaGsonBuilder().setPrettyPrinting().create();
            AnimationProperties animation = mapper.fromJson(text, AnimationProperties.class);
            System.out.println("path = " + path + ".png");
            animation.image = new Image(new File(path + ".png").toURI().toString());
            return animation;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getCount() {
        return count;
    }

    public int getColumns() {
        return columns;
    }

    public Image getImage() {
        return image;
    }

    /*
    public boolean writeToFile(String path) {
        if (path == null)
            return false;
        YaGson mapper = new YaGsonBuilder().setPrettyPrinting().create();
        try {
            File file = new File(path);
            PrintStream result = new PrintStream(file);
            result.println(mapper.toJson(this, AnimationProperties.class));
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    */
}
