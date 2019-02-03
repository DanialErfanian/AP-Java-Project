package Server.Scoreboard;

import Server.User.HostProfile;
import Server.User.ScoreboardProfile;

import java.util.HashMap;

public class Scoreboard {
    private HashMap<String, HostProfile> watchers = new HashMap<>();
    private HashMap<String, ScoreboardProfile> members = new HashMap<>();

    // TODO Broadcast

    public void addMember(ScoreboardProfile scoreboardProfile) {
        members.put(scoreboardProfile.getUsername(), scoreboardProfile);
    }

    public void removeMember(HostProfile hostProfile) {
        members.remove(hostProfile.getUsername());
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
