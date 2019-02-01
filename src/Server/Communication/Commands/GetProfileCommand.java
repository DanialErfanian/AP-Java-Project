package Server.Communication.Commands;

import Server.Communication.Results.GetProfileResult;
import Server.Server;

public class GetProfileCommand {
    private String username;

    public GetProfileResult run() {
        return Server.getInstance().getProfile(username);
    }
}
