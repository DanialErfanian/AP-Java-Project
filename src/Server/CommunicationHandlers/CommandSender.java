package Server.CommunicationHandlers;

import Server.Communication.Commands.BaseCommand;
import Server.Communication.Results.BaseResult;
import Server.Exceptions.BadServerException;
import Utils.NetworkConfig;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CommandSender {
    private Socket socket;
    private ObjectOutputStream serverOutput;
    private ObjectInputStream serverInput;
    private NetworkConfig serverConf;

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
            serverOutput.writeObject(command);
            serverOutput.flush();
            return (BaseResult) serverInput.readObject();
        } catch (IOException e) {
            throw new BadServerException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
