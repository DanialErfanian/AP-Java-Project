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
                    String animalName = args[1];
                    if (game.buy(animalName))
                        System.out.printf("a/an %s bought successfully.\n", animalName);
                    else
                        System.out.println("buying animal failed");
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
                    String workshopName = args[1];
                    System.out.println(game.startWorkshop(workshopName));
                    break;
                case "upgrade":
                    String target = args[1];
                    if (game.upgrade(target))
                        System.out.println("Upgrade done successfully.");
                    else
                        System.out.println("Upgrading failed!");
                case "load_custom":
                    String path = args[1];
                    if (Game.loadCustom(path))
                        System.out.println("Custom level loaded successfully.");
                    else
                        System.out.println("Loading level failed!");
                    break;
                case "run":
                    String mapName = args[1];
                    Game newGame = Game.run(mapName);
                    if (newGame == null)
                        System.out.println("Map with this name not found!");
                    else {
                        game = newGame;
                        System.out.println("Map loaded successfully.");
                    }

                    break;
                case "save_game":
                    path = args[1];
                    System.out.println("Saving " + (game.save(path) ? "done." : "failed!"));
                    break;
                case "load_game":
                    path = args[1];
                    Game loadedGame = Game.load(path);
                    if (loadedGame == null)
                        System.out.println("Loading failed!");
                    else {
                        game = loadedGame;
                        System.out.println("Game loaded successfully.");
                    }
                    break;
                case "print":
                    target = args[1];
                    System.out.println(game.print(target));
                    break;
                case "turn":
                    int turnCount = args.length >= 2 ? Integer.valueOf(args[1]) : 1;
                    game.turn(turnCount);
                    System.out.printf("%d turns done successfully.\n", turnCount);
                    break;
                case "exit":
                    System.exit(0);
                default:
                    switch (args[1]) {
                        case "add":
                            if (game.addToVehicle(args[0], args[2], Integer.parseInt(args[3])))
                                System.out.printf("Adding to %s done successfully.\n", args[0]);
                            else
                                System.out.printf("Adding to %s failed!\n", args[0]);
                            break;
                        case "clear":
                            if (game.clearVehicle(args[0]))
                                System.out.printf("%s cleared successfully.\n", args[0]);
                            else
                                System.out.printf("clearing %s failed!\n", args[0]);
                            break;
                        case "go":
                            if (game.goVehicle(args[0]))
                                System.out.printf("%s gone successfully.\n", args[0]);
                            else
                                System.out.printf("%s can't be gone!\n", args[0]);
                            break;
                        default:
                            System.out.println("Invalid input!");
                    }
            }
        }
    }
}
