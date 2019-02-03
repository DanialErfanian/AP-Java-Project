package Server.Communication.Commands;

import Server.ChatRoom.Message;
import Server.Communication.Results.BaseResult;
import Server.Server;
import Server.User.AuthenticationProfile;

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
        if (message.getSender().equals(this.getAuthenticationProfile().getUsername()))
            return new BaseResult(403);
        Server.getInstance().sendGlobalMessage(message);
        return BaseResult.getOK();
    }
}
