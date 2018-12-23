package FarmFrenzy;

import Buildings.Well;
import Logic.Game;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Well well = new Well(game);
        System.out.println(well.toString());
    }
}
