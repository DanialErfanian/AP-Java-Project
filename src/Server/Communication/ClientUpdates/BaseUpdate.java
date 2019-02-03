package Server.Communication.ClientUpdates;

import java.io.Serializable;

public abstract class BaseUpdate implements Serializable {

    abstract protected void run();

    public void start() {
        run();
    }

}
