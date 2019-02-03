package Server.Communication.Commands;

import Server.Communication.Results.BaseResult;
import Server.Server;
import Server.User.AuthenticationProfile;

public class JoinScoreboardCommand extends BaseCommand {
    protected boolean needsAuthentication() {
        return true;
    }

    public JoinScoreboardCommand(AuthenticationProfile authenticationProfile) {
        super(authenticationProfile);
    }

    protected BaseResult run() {
        return Server.getInstance().addScoreboardWatcher(this.getAuthenticationProfile().getUsername());
    }
}
