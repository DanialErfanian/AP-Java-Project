package Server.Scoreboard;

import Server.User.ScoreboardProfile;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewableScoreboard {
    private HashMap<String, ScoreboardProfile> members;

    ViewableScoreboard(HashMap<String, ScoreboardProfile> members) {
        this.members = members;
    }

    public void addMember(ScoreboardProfile scoreboardProfile) {
        members.put(scoreboardProfile.getUsername(), scoreboardProfile);
    }

    public void removeMember(ScoreboardProfile scoreboardProfile) {
        members.remove(scoreboardProfile.getUsername());
    }

    public ArrayList<ScoreboardProfile> getScoreboardProfiles(){
        return (ArrayList<ScoreboardProfile>) members.values();
    }
}
