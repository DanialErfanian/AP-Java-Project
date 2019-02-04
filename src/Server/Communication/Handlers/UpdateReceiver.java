package Server.Communication.Handlers;

import Server.Communication.ClientUpdates.BaseUpdate;
import Utils.NetworkConfig;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class UpdateReceiver implements Runnable {
    private ServerSocket serverSocket;

    public UpdateReceiver(NetworkConfig netConf) {
        try {
            serverSocket = new ServerSocket(netConf.getPort());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            Socket socket = serverSocket.accept();
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            while (!socket.isClosed()) {
                YaGson mapper = new YaGsonBuilder().setPrettyPrinting().create();
                String updateJson = (String) input.readObject();
                BaseUpdate update = mapper.fromJson(updateJson, BaseUpdate.class);
                System.err.println("new update found :D");
                update.start();
            }
        } catch (Exception e) {
            System.err.println("Error while listening for updates.");
            e.printStackTrace();
        }
    }
}
