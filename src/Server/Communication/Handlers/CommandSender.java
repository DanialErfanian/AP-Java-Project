package Server.Communication.Handlers;

import Server.Communication.Commands.BaseCommand;
import Server.Communication.Results.BaseResult;
import Server.Exceptions.BadServerException;
import Utils.NetworkConfig;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CommandSender {
    private Socket socket;
    private ObjectOutputStream serverOutput;
    private ObjectInputStream serverInput;
    private NetworkConfig serverConf;

    public CommandSender(NetworkConfig serverConf) {
        this.serverConf = serverConf;
    }

    private void connectServer() throws BadServerException {
        System.err.printf("INFO: trying to connect to server %s:%d.\n", serverConf.getIp(), serverConf.getPort());
        try {
            socket = new Socket(serverConf.getIp(), serverConf.getPort());
            serverInput = new ObjectInputStream(socket.getInputStream());
            serverOutput = new ObjectOutputStream(socket.getOutputStream());
            serverOutput.flush();
        } catch (IOException e) {
            System.err.println("Can't connect to server or create input/output stream for it.");
            throw new BadServerException(e);
        }
    }

    public synchronized BaseResult sendCommand(BaseCommand command) throws BadServerException {
        if (socket == null || socket.isClosed()) {
            connectServer();
        }
        try {
            System.err.println("CommandSender sending command: " + command.getClass());
            YaGson mapper = new YaGsonBuilder().setPrettyPrinting().create();
            String json = mapper.toJson(command, BaseCommand.class);
            serverOutput.writeObject(json);
            serverOutput.flush();
            BaseResult result = mapper.fromJson((String) serverInput.readObject(), BaseResult.class);
            System.err.println("CommandSender found result: " + result.getClass());
            return result;
        } catch (IOException e) {
            throw new BadServerException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
