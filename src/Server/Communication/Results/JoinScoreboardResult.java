package Server.Communication.Results;

import Server.Scoreboard.ViewableScoreboard;

public class JoinScoreboardResult extends BaseResult {
    private ViewableScoreboard scoreboard;

    public JoinScoreboardResult(ViewableScoreboard scoreboard) {
        super(200);
        this.scoreboard = scoreboard;
    }
}
