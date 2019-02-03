package Server.Communication.Commands;

import Server.Communication.Results.BaseResult;
import Server.Server;
import Server.User.AuthenticationProfile;

public class LeaveScoreboardCommand extends BaseCommand {
    protected boolean needsAuthentication() {
        return true;
    }

    public LeaveScoreboardCommand(AuthenticationProfile authenticationProfile) {
        super(authenticationProfile);
    }

    protected BaseResult run() {
        Server.getInstance().leaveScoreboard(getAuthenticationProfile().getUsername());
        return BaseResult.getOK();
    }
}
