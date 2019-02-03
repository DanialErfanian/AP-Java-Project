package Server.Communication.ClientUpdates;

import Server.Client;
import Server.User.ScoreboardProfile;

public class ScoreboardChangeUpdate extends BaseUpdate {
    private ScoreboardProfile scoreboardProfile;

    protected void run() {
        Client.getInstance().scoreboardUpdate(scoreboardProfile);
    }
}
