package Server.Communication.Commands;

import Server.Communication.Results.BaseResult;
import Server.Server;

public class LeaveScoreboardCommand extends BaseCommand {
    final private boolean needsAuthentication = true;

    protected BaseResult run() {
        Server.getInstance().leaveScoreboard(getAuthenticationProfile().getUsername());
        return BaseResult.getOK();
    }
}
