package Server.Communication.Commands;

import Server.Communication.Results.GetProfileResult;
import Server.Server;

public class GetProfileCommand extends BaseCommand {
    private String username;

    public GetProfileCommand(String username) {
        this.username = username;
    }

    protected GetProfileResult run() {
        return Server.getInstance().getProfile(username);
    }
}
