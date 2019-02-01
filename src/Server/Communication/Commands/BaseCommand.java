package Server.Communication.Commands;

import Server.Communication.Results.BaseResult;
import Server.Server;
import Server.User.AuthenticationProfile;

public abstract class BaseCommand {
    private AuthenticationProfile authenticationProfile;
    private boolean needsAuthentication = false;

    public BaseCommand() {
    }

    public BaseCommand(AuthenticationProfile authenticationProfile) {
        this.authenticationProfile = authenticationProfile;
    }

    protected boolean isAuthenticated() {
        return Server.getInstance().authenticates(this.getAuthenticationProfile());
    }

    public AuthenticationProfile getAuthenticationProfile() {
        return authenticationProfile;
    }

    public BaseResult start() {
        if (needsAuthentication && !isAuthenticated())
            return new BaseResult(403);
        return run();
    }

    public abstract BaseResult run();
}
