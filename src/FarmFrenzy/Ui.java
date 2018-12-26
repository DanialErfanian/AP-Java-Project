package FarmFrenzy;

import Logic.Game;

import java.util.Scanner;

class Ui {
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
                    int x = Integer.parseInt(args[1]);
                    int y = Integer.parseInt(args[2]);
                    game.collect(x, y);
                    System.out.printf("cell (%d, %d) collected successfully.\n", x, y);
                    break;
                case "cage":
                    x = Integer.parseInt(args[1]);
                    y = Integer.parseInt(args[2]);
                    game.cage(x, y);
                    System.out.printf("wild animals in cell (%d, %d) catched successfully.\n", x, y);
                    break;
                case "plant":
                    x = Integer.parseInt(args[1]);
                    y = Integer.parseInt(args[2]);
                    if (game.plant(x, y))
                        System.out.printf("plant growing in cell (%d, %d) done successfully.\n", x, y);
                    else
                        System.out.printf("plant growing in cell (%d, %d) failed.\n", x, y);
                    break;
                case "well":
                    if (game.well())
                        System.out.println("well filled successfully.");
                    else
                        System.out.println("well filling failed.");
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
                    System.out.printf("%d turns done successfully.\n", turnCount);
                    break;
                case "exit":
                    System.exit(0);
            }
        }
    }
}
