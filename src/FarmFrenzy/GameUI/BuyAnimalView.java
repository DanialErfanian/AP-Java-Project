package FarmFrenzy.GameUI;

import Animals.AnimalType;
import Logic.Game;
import Utils.ButtonHandler;
import Utils.ButtonProperties;
import javafx.scene.Group;

public class BuyAnimalView {
    private ButtonProperties properties;
    private AnimalType type;

    public BuyAnimalView(ButtonProperties properties, AnimalType type) {
        this.properties = properties;
        this.type = type;
    }

    public Group build(Game game) {
        ButtonHandler button = new ButtonHandler(properties) {
            @Override
            protected boolean isAvailable() {
                return game.getMoney() >= game.getAnimalBuyCost(type);
            }

            @Override
            public void onClick() {
                game.buy(type);
            }

            @Override
            protected String getText() {
                return Integer.toString(game.getAnimalBuyCost(type));
            }
        };
        return button.build();
    }
}
