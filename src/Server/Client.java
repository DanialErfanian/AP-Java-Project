package Server;

import Server.ChatRoom.Message;
import Server.Communication.Commands.*;
import Server.Communication.Handlers.CommandSender;
import Server.Communication.Handlers.UpdateReceiver;
import Server.Communication.Results.BaseResult;
import Server.Communication.Results.GetProfileResult;
import Server.Communication.Results.JoinScoreboardResult;
import Server.Communication.Results.RegisterResult;
import Server.Exceptions.BadServerException;
import Server.Exceptions.NonuniqueUsernameException;
import Server.Exceptions.StatusCodeException;
import Server.Scoreboard.ViewableScoreboard;
import Server.User.HostProfile;
import Server.User.RegisterProfile;
import Server.User.ScoreboardProfile;
import Utils.NetworkConfig;
import org.jetbrains.annotations.Contract;

public class Client {
    private Client() {
    }

    private static Client instance = new Client();

    @Contract(pure = true)
    public static Client getInstance() {
        return instance;
    }

    private HostProfile profile;
    private CommandSender commandSender;
    private UpdateReceiver updateReceiver;
    private NetworkConfig clientNetConf;

    private ViewableScoreboard scoreboard;

    public void runUpdateReceiver() {
        updateReceiver = new UpdateReceiver(clientNetConf);
        Thread updateReceiverThread = new Thread(updateReceiver);
        updateReceiverThread.start();
    }

    public void joinServer() throws BadServerException, StatusCodeException {
        RegisterProfile registerProfile = profile.toRegisterProfile();
        registerProfile.setNetConf(clientNetConf);
        RegisterResult result = (RegisterResult) commandSender.sendCommand(new RegisterCommand(registerProfile));
        if (result.getStatusCode() == 400)
            throw new NonuniqueUsernameException(400);
        if (result.getStatusCode() != 200)
            throw new StatusCodeException(result.getStatusCode());
        profile.setToken(result.getAuthenticationProfile().getToken());
        profile.getNetConf().setIp(result.getIp());
    }

    public void leaveServer() throws BadServerException, StatusCodeException {
        LeaveServerCommand command = new LeaveServerCommand(profile.toAuthenticationProfile());
        BaseResult result = commandSender.sendCommand(command);
        if (result.getStatusCode() != 200)
            throw new StatusCodeException(result.getStatusCode());
    }

    public void joinScoreboard() throws BadServerException, StatusCodeException {
        JoinScoreboardCommand command = new JoinScoreboardCommand(profile.toAuthenticationProfile());
        JoinScoreboardResult result = (JoinScoreboardResult) commandSender.sendCommand(command);
        if (result.getStatusCode() != 200)
            throw new StatusCodeException(result.getStatusCode());
        scoreboard = result.getScoreboard();
    }

    public void leaveScoreboard() throws BadServerException, StatusCodeException {
        LeaveScoreboardCommand command = new LeaveScoreboardCommand(profile.toAuthenticationProfile());
        BaseResult result = commandSender.sendCommand(command);
        if (result.getStatusCode() != 200)
            throw new StatusCodeException(result.getStatusCode());
    }

    public void updateMoney(int money) throws BadServerException, StatusCodeException {
        UpdateMoneyCommand command = new UpdateMoneyCommand(profile.toAuthenticationProfile(), money);
        BaseResult result = commandSender.sendCommand(command);
        if (result.getStatusCode() != 200)
            throw new StatusCodeException(result.getStatusCode());
    }

    public void sendMessage(Message message) throws BadServerException, StatusCodeException {
        SendMessageCommand command = new SendMessageCommand(profile.toAuthenticationProfile(), message);
        BaseResult result = commandSender.sendCommand(command);
        if (result.getStatusCode() != 200)
            throw new StatusCodeException(result.getStatusCode());
    }

    public HostProfile getProfile(String username) throws BadServerException, StatusCodeException {
        GetProfileCommand command = new GetProfileCommand(username);
        GetProfileResult result = (GetProfileResult) commandSender.sendCommand(command);
        if (result.getStatusCode() != 200)
            throw new StatusCodeException(result.getStatusCode());
        return result.getHostProfile();
    }

    public void addMessageUpdate(Message message) {
        // TODO
    }

    public void scoreboardUpdate(ScoreboardProfile scoreboardProfile) {
        scoreboard.addMember(scoreboardProfile);
    }

    public void scoreboardLeave(ScoreboardProfile scoreboardProfile) {
        scoreboard.removeMember(scoreboardProfile);
    }
}
