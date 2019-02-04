package Server.Communication.Handlers;

import Server.Communication.Commands.BaseCommand;
import Server.Communication.Results.BaseResult;
import Server.Communication.Results.GetIpResult;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CommandReceiver implements Runnable {
    private Socket socket;

    public CommandReceiver(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        ObjectInputStream input;
        ObjectOutputStream output;
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.err.println("Can't open input/output for the socket.");
            e.printStackTrace();
            return;
        }

        while (!socket.isClosed()) {
            try {
                Object data = input.readObject();
                if (data == null)
                    break;
                String json = (String) data;
                YaGson mapper = new YaGsonBuilder().setPrettyPrinting().create();
                BaseCommand command;
                command = mapper.fromJson(json, BaseCommand.class);
                System.err.println("CommandReceiver new Command: " + command.getClass());
                BaseResult result = command.start();
                System.err.println("CommandReceiver sending result to client \nresult: " + result.getClass());
                if (result instanceof GetIpResult)
                    ((GetIpResult) result).setIp(socket.getInetAddress().getHostAddress());
                String resultJson = mapper.toJson(result, BaseResult.class);
                output.writeObject(resultJson);
                output.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

