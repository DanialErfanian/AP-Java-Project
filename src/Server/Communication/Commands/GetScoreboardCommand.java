package Server.Communication.Commands;

import Server.Communication.Results.GetScoreboardResult;
import Server.Server;

public class GetScoreboardCommand extends BaseCommand {
    protected GetScoreboardResult run() {
        return new GetScoreboardResult(Server.getInstance().getPureScoreboardz().toViewableScoreboard());
    }
}
