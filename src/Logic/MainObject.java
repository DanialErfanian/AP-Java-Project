package Logic;

abstract public class MainObject {
    private Game game;

    public Game getGame() {
        return game;
    }

    public MainObject(Game game) {
        this.game = game;
    }

    protected abstract void increaseTurn();

}
