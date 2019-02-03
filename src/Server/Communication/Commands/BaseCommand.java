package Server.Communication.Commands;

import Server.Communication.Results.BaseResult;
import Server.Server;
import Server.User.AuthenticationProfile;

import java.io.Serializable;

public abstract class BaseCommand implements Serializable {
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

    protected boolean needsAuthentication() {
        return false;
    }

    protected abstract BaseResult run();
}
