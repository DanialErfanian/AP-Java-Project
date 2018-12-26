package FarmFrenzy;

import Logic.Game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();
        String type = scanner.next();
        int x, y;
        switch (type) {
            case "pickup":
                x = scanner.nextInt();
                y = scanner.nextInt();
                game.collect(x, y);
                break;
            case "cage":
                x = scanner.nextInt();
                y = scanner.nextInt();
                game.cage(x, y);
                break;
            case "plant":
                x = scanner.nextInt();
                y = scanner.nextInt();
                System.out.println(game.plant(x, y));
                break;
            case "well":
                System.out.println(game.well());
            case "turn":
                game.turn(scanner.nextInt());
        }
    }
}
