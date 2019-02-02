package Server.Communication.Commands;

import Server.Communication.Results.BaseResult;
import Server.Server;

public class JoinScoreboardCommand extends BaseCommand {
    private boolean needsAuthentication() {
        return true;
    }
    protected BaseResult run() {
        return Server.getInstance().addScoreboardWatcher(this.getAuthenticationProfile().getUsername());
    }
}
