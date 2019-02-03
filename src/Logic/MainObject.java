package Logic;

abstract public class MainObject implements java.io.Serializable {
    private Game game;

    public Game getGame() {
        return game;
    }

    public MainObject(Game game) {
        this.game = game;
    }

    protected abstract void increaseTurn();

}
