package Server.Communication.Commands;

import Server.Communication.Results.BaseResult;
import Server.Server;
import Server.User.AuthenticationProfile;

public abstract class BaseCommand {
    private AuthenticationProfile authenticationProfile;

    BaseCommand() {
    }

    BaseCommand(AuthenticationProfile authenticationProfile) {
        this.authenticationProfile = authenticationProfile;
    }

    private boolean isAuthenticated() {
        return Server.getInstance().authenticates(this.getAuthenticationProfile());
    }

    AuthenticationProfile getAuthenticationProfile() {
        return authenticationProfile;
    }

    public BaseResult start() {
        if (needsAuthentication() && !isAuthenticated())
            return new BaseResult(403);
        return run();
    }

    private boolean needsAuthentication() {
        return false;
    }

    public abstract BaseResult run();
}
