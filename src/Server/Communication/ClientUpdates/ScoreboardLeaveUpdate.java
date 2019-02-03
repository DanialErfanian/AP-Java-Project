package Server.Communication.ClientUpdates;

import Server.Client;
import Server.User.ScoreboardProfile;

public class ScoreboardLeaveUpdate extends BaseUpdate {
    private ScoreboardProfile scoreboardProfile;

    protected void run() {
        Client.getInstance().scoreboardLeave(scoreboardProfile);
    }
}
