package network.multichat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class MultiChatServer {
    private Map<Socket, PrintWriter> socketMap = new HashMap<>();
    public static void main(String[] args) {
        MultiChatServer multiChatServer = new MultiChatServer();
    }

    public MultiChatServer(){
        try {
            ServerSocket serverSocket = new ServerSocket(6780);
            System.out.println("Klar at modtage klient");
            while(true){
                Socket socket = serverSocket.accept(); // blokerer
                System.out.println("Har modtaget klient");
                socketMap.put(socket, new PrintWriter(socket.getOutputStream(), true));
                ClientHandler clientHandler = new ClientHandler(socket, this);
                Thread thread = new Thread(clientHandler); // en ny seperat proces, som kører uafhængig
                // af main thread.
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendToAll(String message) {
        System.out.println(message);
        for (Map.Entry<Socket, PrintWriter> entry: socketMap.entrySet()){
            entry.getValue().println(message);
        }
    }
}










