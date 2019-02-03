package Server.ChatRoom;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private Room room;
    private String sender;
    private String text;
    private Message repliedMessage;
    private Date timestamp;

    public Message(String sender, String text, Message repliedMessage) {
        this.sender = sender;
        this.text = text;
        this.repliedMessage = repliedMessage;
        this.timestamp = new Date();
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getSender() {
        return sender;
    }

    public String getText() {
        return text;
    }

    public String getCompleteText() {
        return getSender() + ": " + getText();
    }
}
