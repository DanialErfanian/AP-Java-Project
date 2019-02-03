package Server.ChatRoom;

import Server.Server;
import Server.User.HostProfile;

import java.util.ArrayList;
import java.util.HashSet;

public class Room {
    private ArrayList<Message> history = new ArrayList<>();
    private HashSet<HostProfile> users = new HashSet<>();

    public void addMember(HostProfile hostProfile) {
        users.add(hostProfile);
    }

    public void addMessage(Message message) {
        //message.setRoom(this);
        this.history.add(message);
        for (HostProfile user : users) {
            Server.getInstance().sendMessageToUser(user, message);
        }
    }

    public void removeMember(HostProfile hostProfile) {
        users.remove(hostProfile);
    }
}
