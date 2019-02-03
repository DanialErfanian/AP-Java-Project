package Server.Communication.ClientUpdates;

import Server.ChatRoom.Message;
import Server.Client;

public class ChatMessageUpdate extends BaseUpdate {
    private Message message;

    public ChatMessageUpdate(Message message) {
        this.message = message;
    }

    protected void run() {
        Client.getInstance().addMessageUpdate(message);
    }
}
