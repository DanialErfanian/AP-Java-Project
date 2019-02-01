package Server.Communication.Commands;

import Server.ChatRoom.Message;
import Server.Communication.Results.BaseResult;
import Server.Server;

public class SendMessageCommand extends BaseCommand {
    private Message message;

    public BaseResult run() {
        if (this.isAuthenticated())
            return BaseResult.getNOK();
        Server.getInstance().getGlobalRoom().addMessage(message);
        return BaseResult.getOK();
    }
}
