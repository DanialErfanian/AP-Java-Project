package Utils;

public class ButtonProperties {
    private ImageProperties image;
    private int textX, textY;

    public ButtonProperties(ImageProperties image, int textX, int textY) {
        this.image = image;
        this.textX = textX;
        this.textY = textY;
    }

    public ImageProperties getImage() {
        return image;
    }

    public int getTextX() {
        return textX;
    }

    public int getTextY() {
        return textY;
    }
}
