package Server.CommunicationHandlers;

import Server.Communication.Commands.BaseCommand;

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
            System.out.println("Can't open input/output for the socket.");
            e.printStackTrace();
            return;
        }

        while (!socket.isClosed()) {
            try {
                Object data = input.readObject();
                if (data == null)
                    break;
                BaseCommand command = (BaseCommand) data;
                output.writeObject(command.start());
                output.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

