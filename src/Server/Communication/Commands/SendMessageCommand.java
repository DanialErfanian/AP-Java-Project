package Server.Communication.Commands;

import Server.ChatRoom.Message;
import Server.Communication.Results.BaseResult;
import Server.Server;
import Server.User.AuthenticationProfile;
import Server.User.HostProfile;

public class SendMessageCommand extends BaseCommand {
    private Message message;

    protected boolean needsAuthentication() {
        return true;
    }


    public SendMessageCommand(AuthenticationProfile authenticationProfile, Message message) {
        super(authenticationProfile);
        this.message = message;
    }

    protected BaseResult run() {
        Server server = Server.getInstance();
        server.sendGlobalMessage(message);
        return BaseResult.getOK();
    }
}
