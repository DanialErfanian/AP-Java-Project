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
            if(command.equals(""))
                continue;
            String[] args = command.split(" ");
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
                default:
                    vehicleCommand(game, args);
            }
        }
    }

    private static void vehicleCommand(Game game, String[] args) {
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

    private static void turn(Game game, String[] args) {
        int turnCount = Integer.parseInt(args[1]);
        game.turn(turnCount);
        System.out.printf("%d turns done successfully.\n", turnCount);
    }

    private static void print(Game game, String[] args) {
        String target = args[1];
        System.out.println(game.print(target));
    }

    private static Game loadGame(Game game, String[] args) {
        String path = args[1];
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
        String path = args[1];
        System.out.println("Saving " + (game.save(path) ? "done." : "failed!"));
    }

    private static Game run(Game game, String[] args) {
        String mapName = args[1];
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
        String path = args[1];
        if (Game.loadCustom(path))
            System.out.println("Custom level loaded successfully.");
        else
            System.out.println("Loading level failed!");
    }

    private static void upgrade(Game game, String[] args) {
        String target = args[1];
        if (game.upgrade(target))
            System.out.println("Upgrade done successfully.");
        else
            System.out.println("Upgrading failed!");
    }

    private static void start(Game game, String[] args) {
        String workshopName = args[1];
        System.out.println(game.startWorkshop(workshopName));
    }

    private static void well(Game game) {
        if (game.well())
            System.out.println("well filled successfully.");
        else
            System.out.println("well filling failed.");
    }

    private static void plant(Game game, String[] args) {
        int x = Integer.parseInt(args[1]);
        int y = Integer.parseInt(args[2]);
        if (game.plant(x, y))
            System.out.printf("plant growing in cell (%d, %d) done successfully.\n", x, y);
        else
            System.out.printf("plant growing in cell (%d, %d) failed.\n", x, y);
    }

    private static void cage(Game game, String[] args) {
        int x = Integer.parseInt(args[1]);
        int y = Integer.parseInt(args[2]);
        game.cage(x, y);
        System.out.printf("wild animals in cell (%d, %d) catched successfully.\n", x, y);
    }

    private static void pickup(Game game, String[] args) {
        int x = Integer.parseInt(args[1]);
        int y = Integer.parseInt(args[2]);
        game.collect(x, y);
        System.out.printf("cell (%d, %d) collected successfully.\n", x, y);
    }

    private static void buyAnimal(Game game, String[] args) {
        String animalName = args[1];
        if (game.buy(animalName))
            System.out.printf("a/an %s bought successfully.\n", animalName);
        else
            System.out.println("buying animal failed!");
    }

}
