package network.objects;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ObjectServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6780);
            Socket socket = serverSocket.accept();
            System.out.println("forbindelse oprettet til client");
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Message message = (Message) objectInputStream.readObject();
            message.verify();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(message);
            objectInputStream.close();
            objectOutputStream.close();
            socket.close();
            serverSocket.close();
        }catch (Exception e){

        }
    }
}
