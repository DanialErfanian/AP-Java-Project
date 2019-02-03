package Server.Communication.Results;

import Server.Scoreboard.ViewableScoreboard;

public class GetScoreboardResult extends BaseResult {
    private ViewableScoreboard scoreboard;

    public GetScoreboardResult(ViewableScoreboard scoreboard) {
        super(200);
        this.scoreboard = scoreboard;
    }

    public ViewableScoreboard getScoreboard() {
        return scoreboard;
    }
}
