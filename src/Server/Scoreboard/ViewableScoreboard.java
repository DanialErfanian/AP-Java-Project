package Server.Scoreboard;

import Server.User.ScoreboardProfile;

import java.util.HashMap;

public class ViewableScoreboard {
    private HashMap<String, ScoreboardProfile> members = new HashMap<>();

    public ViewableScoreboard(HashMap<String, ScoreboardProfile> members) {
        this.members = members;
    }

    public void addMember(ScoreboardProfile scoreboardProfile) {
        members.put(scoreboardProfile.getUsername(), scoreboardProfile);
    }
}
