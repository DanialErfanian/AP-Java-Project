package Server.Communication.Commands;

import Server.Communication.Results.BaseResult;
import Server.Server;
import Server.User.AuthenticationProfile;

public abstract class BaseCommand {
    private AuthenticationProfile authenticationProfile;

    public BaseCommand() {
    }

    protected boolean isAuthenticated() {
        return Server.getInstance().authenticates(this.getAuthenticationProfile());
    }

    public AuthenticationProfile getAuthenticationProfile() {
        return authenticationProfile;
    }

    public abstract BaseResult run();
}
