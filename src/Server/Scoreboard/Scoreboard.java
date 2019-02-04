package Server.Scoreboard;

import Server.Communication.ClientUpdates.BaseUpdate;
import Server.Communication.ClientUpdates.ScoreboardChangeUpdate;
import Server.Communication.ClientUpdates.ScoreboardLeaveUpdate;
import Server.User.HostProfile;
import Server.User.ScoreboardProfile;

import java.util.HashMap;

public class Scoreboard {
    private HashMap<String, HostProfile> watchers = new HashMap<>();
    private HashMap<String, ScoreboardProfile> members = new HashMap<>();

    // TODO Broadcast implement to resolve -_-

    public void addMember(ScoreboardProfile scoreboardProfile) {
        members.put(scoreboardProfile.getUsername(), scoreboardProfile);
        ScoreboardChangeUpdate update = new ScoreboardChangeUpdate(scoreboardProfile);
        updateWatchers(update);
    }

    private void updateWatchers(BaseUpdate update) {
        for (HostProfile watcher : watchers.values())
            watcher.sendUpdate(update);
    }

    public void removeMember(HostProfile hostProfile) {
        String username = hostProfile.getUsername();
        ScoreboardProfile scoreboardProfile = members.remove(username);
        ScoreboardLeaveUpdate update = new ScoreboardLeaveUpdate(scoreboardProfile);
        updateWatchers(update);
    }

    public void addWatcher(HostProfile hostProfile) {
        watchers.put(hostProfile.getUsername(), hostProfile);
    }

    public void removeWatcher(HostProfile hostProfile) {
        watchers.remove(hostProfile.getUsername());
    }

    public ViewableScoreboard toViewableScoreboard() {
        return new ViewableScoreboard(members);
    }
}
