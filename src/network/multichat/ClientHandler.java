package network.multichat;

import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {

    private MultiChatServer multiChatServer;
    private Socket socket;
    public ClientHandler(Socket socket, MultiChatServer multiChatServer){
        this.socket = socket;
        this.multiChatServer = multiChatServer;
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(socket.getInputStream());
            String message = "";
            while(true){
                message = scanner.nextLine();
                multiChatServer.sendToAll(message);
            }

        }catch (Exception e){

        }
    }
}
