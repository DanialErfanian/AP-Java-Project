package Server.Communication.Commands;

import Server.Communication.Results.BaseResult;
import Server.Server;
import Server.User.AuthenticationProfile;

public class LeaveServerCommand extends BaseCommand {
    protected boolean needsAuthentication() {
        return true;
    }

    public LeaveServerCommand(AuthenticationProfile authenticationProfile) {
        super(authenticationProfile);
    }

    protected BaseResult run() {
        Server.getInstance().leaveMember(this.getAuthenticationProfile());
        return new BaseResult(200);
    }
}
