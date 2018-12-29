package FarmFrenzy;

import Logic.Game;
import Utils.Position;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Scanner;

class Ui {
    private Ui() {

    }

    static void Run(Game game) {
        Scanner input = new Scanner(System.in);
        while (true) {
            String command = input.nextLine();
            if (command.length() == 0)
                continue;
            String[] args = command.toLowerCase().split(" ");
            switch (args[0]) {
                case "buy":
                    buyAnimal(game, args);
                    break;
                case "pickup":
                    pickup(game, args);
                    break;
                case "cage":
                    cage(game, args);
                    break;
                case "plant":
                    plant(game, args);
                    break;
                case "well":
                    well(game);
                    break;
                case "start":
                    start(game, args);
                    break;
                case "upgrade":
                    upgrade(game, args);
                    break;
                case "load_custom":
                    loadCustom(args);
                    break;
                case "run":
                    game = run(game, args);
                    break;
                case "save_game":
                    saveGame(game, args);
                    break;
                case "load_game":
                    game = loadGame(game, args);
                    break;
                case "print":
                    print(game, args);
                    break;
                case "turn":
                    turn(game, args);
                    break;
                case "exit":
                    System.exit(0);
                case "clear":
                    try {
                        System.out.println("Fana e ozma :)");
                        Runtime.getRuntime().exec("clear");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    vehicleCommand(game, args);
            }
        }
    }

    private static void vehicleCommand(Game game, String[] args) {
        if (args.length < 2) {
            invalidInput();
            return;
        }
        switch (args[1]) {
            case "add":
                addToVehicle(game, args);
                break;
            case "clear":
                clearVehicle(game, args);
                break;
            case "go":
                goVehicle(game, args);
                break;
            default:
                System.out.println("Invalid input!");
        }
    }

    private static void addToVehicle(Game game, String[] args) {
        if (args.length != 4) {
            invalidInput();
            return;
        }
        try {
            if (game.addToVehicle(args[0], args[2], Integer.parseInt(args[3])))
                System.out.printf("Adding to %s done successfully.\n", args[0]);
            else
                System.out.printf("Adding to %s failed!\n", args[0]);
        } catch (NumberFormatException e) {
            invalidInput();
        }
    }

    private static void clearVehicle(Game game, String[] args) {
        if (game.clearVehicle(args[0]))
            System.out.printf("%s cleared successfully.\n", args[0]);
        else
            System.out.printf("clearing %s failed!\n", args[0]);
    }

    static private void goVehicle(Game game, String[] args) {
        if (game.goVehicle(args[0]))
            System.out.printf("%s gone successfully.\n", args[0]);
        else
            System.out.printf("%s can't be gone!\n", args[0]);
    }

    private static void turn(Game game, String[] args) {
        try {
            int turnCount = Integer.parseInt(args[1]);
            game.turn(turnCount);
            System.out.printf("%d turns done successfully.\n", turnCount);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            invalidInput();
        }
    }

    private static void print(Game game, String[] args) {
        if (args.length > 1)
            System.out.println(game.print(args[1]));
        else
            invalidInput();
    }

    private static Game loadGame(Game game, String[] args) {
        String path = null;
        if (args.length > 1)
            path = args[1];
        Game loadedGame = Game.load(path);
        if (loadedGame == null)
            System.out.println("Loading failed!");
        else {
            game = loadedGame;
            System.out.println("Game loaded successfully.");
        }
        return game;
    }

    private static void saveGame(Game game, String[] args) {
        String path = null;
        if (args.length > 1)
            path = args[1];
        System.out.println("Saving " + (game.save(path) ? "done." : "failed!"));
    }

    private static Game run(Game game, String[] args) {
        String mapName = null;
        if (args.length > 1)
            mapName = args[1];
        Game newGame = Game.run(mapName);
        if (newGame == null)
            System.out.println("Map with this name not found!");
        else {
            game = newGame;
            System.out.println("Map loaded successfully.");
        }
        return game;
    }

    private static void loadCustom(String[] args) {
        String path = null;
        if (args.length > 1)
            path = args[1];
        if (Game.loadCustom(path))
            System.out.println("Custom level loaded successfully.");
        else
            System.out.println("Loading level failed!");
    }

    private static void upgrade(Game game, String[] args) {
        String target = null;
        if (args.length > 1)
            target = args[1];
        if (game.upgrade(target))
            System.out.println("Upgrade done successfully.");
        else
            System.out.println("Upgrading failed!");
    }

    private static void start(Game game, String[] args) {
        String workshopName = null;
        if (args.length > 1)
            workshopName = args[1];
        System.out.println(game.startWorkshop(workshopName));
    }

    private static void well(Game game) {
        if (game.well())
            System.out.println("well filled successfully.");
        else
            System.out.println("well filling failed.");
    }

    private static void plant(Game game, String[] args) {
        Position position = getPosition(args);
        if (game.plant(position))
            System.out.printf("plant growing in cell %s done successfully.\n", position);
        else
            invalidCell();
    }

    private static void cage(Game game, String[] args) {
        Position position = getPosition(args);
        if (position != null && game.cage(position))
            System.out.printf("wild animals in cell %s catched successfully.\n", position);
        else
            invalidCell();
    }

    private static void pickup(Game game, String[] args) {
        Position position = getPosition(args);
        if (game.collect(position))
            System.out.printf("Cell %s collected successfully.\n", position);
        else
            invalidCell();
    }

    private static void buyAnimal(Game game, String[] args) {
        if (args.length == 1) {
            invalidInput();
            return;
        }
        String animalName = args[1];
        if (game.buy(animalName))
            System.out.printf("a/an %s bought successfully.\n", animalName);
        else
            System.out.println("buying animal failed!");
    }

    private static void invalidInput() {
        System.out.println("Invalid input!");
    }

    private static void invalidCell() {
        System.out.println("Cell isn't inside of map!");
    }

    @Nullable
    private static Position getPosition(String[] args) {
        if (args.length != 3) {
            invalidInput();
            return null;
        }
        try {
            int x = Integer.parseInt(args[1]);
            int y = Integer.parseInt(args[2]);
            return new Position(x, y);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
