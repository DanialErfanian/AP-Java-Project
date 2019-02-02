package Server;

import Server.ChatRoom.Message;
import Server.ChatRoom.Room;
import Server.Communication.Results.GetProfileResult;
import Server.Communication.Results.JoinScoreboardResult;
import Server.Communication.Results.RegisterResult;
import Server.CommunicationHandlers.CommandHandler;
import Server.Scoreboard.Scoreboard;
import Server.User.AuthenticationProfile;
import Server.User.HostProfile;
import Server.User.RegisterProfile;
import Server.User.ScoreboardProfile;
import Utils.NetworkConfig;
import org.jetbrains.annotations.Contract;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;


public class Server {
    private HashMap<String, HostProfile> users = new HashMap<>();
    private Room globalRoom = new Room();
    private Scoreboard scoreboard = new Scoreboard();

    NetworkConfig netConf;

    private Server() {
    }

    private static Server instance = new Server();

    @Contract(pure = true)
    public static Server getInstance() {
        return instance;
    }

    public void run() throws IOException {
        Server server = Server.getInstance();
        ServerSocket serverSocket;
        serverSocket = new ServerSocket(4444);

        while (!serverSocket.isClosed()) {
            Thread thread = new Thread(new CommandHandler(serverSocket.accept()));
            thread.start();
        }
    }

    public Room getGlobalRoom() {
        return globalRoom;
    }

    public boolean authenticates(AuthenticationProfile authenticationProfile) {
        HostProfile hostProfile = users.get(authenticationProfile.getUsername());
        return hostProfile != null && hostProfile.getToken().equals(authenticationProfile.getToken());
    }

    public RegisterResult registerUser(RegisterProfile user) {
        HostProfile hostProfile = users.get(user.getUsername());
        if (hostProfile != null)
            return new RegisterResult(400, null);
        hostProfile = new HostProfile(user.getUsername(), user.getName(), user.getNetConf());
        this.users.put(hostProfile.getToken(), hostProfile);
        this.scoreboard.addMember(hostProfile.toScoreboardProfile());
        return new RegisterResult(hostProfile.toAuthenticationProfile());
    }

    public GetProfileResult getProfile(String username) {
        return new GetProfileResult(users.get(username));
    }

    public void sendMessageToUser(HostProfile user, Message message) {
        // TODO WHAT TO DO? :))
    }

    public void sendGlobalMessage(Message message) {
        globalRoom.addMessage(message);
    }

    public ArrayList<ScoreboardProfile> getScoreboard() {
        ArrayList<ScoreboardProfile> scoreboard = new ArrayList<>();
        for (HostProfile hostProfile : users.values()) {
            scoreboard.add(hostProfile.toScoreboardProfile());
        }
        scoreboard.sort((ScoreboardProfile u1, ScoreboardProfile u2) -> (u2.getMoney() - u1.getMoney()));
        return scoreboard;
    }

    public void updateMoney(String username, int money) {
        HostProfile user = users.get(username);
        user.setMoney(money);
        scoreboard.addMember(user.toScoreboardProfile());
    }

    public JoinScoreboardResult addScoreboardWatcher(String username) {
        scoreboard.addWatcher(users.get(username));
        return new JoinScoreboardResult(scoreboard.toViewableScoreboard());
    }

    public void leaveScoreboard(String username) {
        scoreboard.removeWatcher(users.get(username));
    }
}
