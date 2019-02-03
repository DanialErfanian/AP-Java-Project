package FarmFrenzy;

import Logic.Game;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(null);
        Ui.Run(game);
    }
}
