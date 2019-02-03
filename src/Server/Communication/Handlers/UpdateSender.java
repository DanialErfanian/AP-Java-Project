package Server.Communication.Handlers;

import Server.Communication.ClientUpdates.BaseUpdate;
import Utils.NetworkConfig;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UpdateSender {
    private NetworkConfig netConf;
    private Socket socket;
    private ObjectOutputStream output;

    public void sendUpdate(BaseUpdate update) {
        try {
            if (socket == null)
                connect();
            output.writeObject(update);
        } catch (Exception e) {
            System.err.println("Error while writing object to socket.");
            e.printStackTrace();
        }
    }

    private void connect() throws IOException {
        socket = new Socket(netConf.getIp(), netConf.getPort());
    }

}
