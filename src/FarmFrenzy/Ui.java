package FarmFrenzy;

import Logic.Game;

import java.util.Scanner;

public class Ui {
    private Ui() {

    }

    static void Run(Game game) {
        Scanner input = new Scanner(System.in);
        while (true) {
            String command = input.nextLine();
            String[] args = command.split(" ");
            switch (args[0]) {
                case "buy":
                    break;
                case "pickup":
                    break;
                case "cage":
                    break;
                case "plant":
                    break;
                case "well":
                    break;
                case "start":
                    break;
                case "upgrade":
                    break;
                case "load_custom":
                    break;
                case "run":
                    break;
                case "save_game":
                    break;
                case "load_game":
                    break;
                case "print":
                    break;
                case "turn":
                    int turnCount = args.length >= 2 ? Integer.valueOf(args[1]) : 1;
                    game.turn(turnCount);
                    System.out.printf("%d turns done successfully.", turnCount);
                    break;
            }
        }
    }
}
