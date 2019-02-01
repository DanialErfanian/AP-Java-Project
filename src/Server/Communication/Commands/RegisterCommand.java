package Server.Communication.Commands;

import Server.Communication.Results.RegisterResult;
import Server.Server;
import Server.User.RegisterProfile;

public class RegisterCommand extends BaseCommand {
    private RegisterProfile registerProfile;

    public RegisterResult run() {
        return Server.getInstance().registerUser(registerProfile);
    }
}
