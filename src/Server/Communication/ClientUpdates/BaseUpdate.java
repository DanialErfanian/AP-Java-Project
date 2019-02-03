package Server.Communication.ClientUpdates;

public abstract class BaseUpdate {

    abstract protected void run();

    public void start() {
        run();
    }

}
