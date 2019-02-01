package Server.ChatRoom;

import Server.User.HostProfile;

public class Message {
    private Room room;
    private HostProfile sender;
    private String text;
    private Message repliedMessage;

    public Message(HostProfile sender, String text, Message repliedMessage) {
        this.sender = sender;
        this.text = text;
        this.repliedMessage = repliedMessage;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
