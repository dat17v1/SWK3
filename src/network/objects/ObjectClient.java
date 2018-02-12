package network.objects;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ObjectClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 6780);
            Message message = new Message();
            System.out.println("forbindelse oprettet til server");
            message.setContent("this is boring sometimes!");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(message);
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            message = (Message)objectInputStream.readObject();
            System.out.println("Content: " + message.getContent());
            objectInputStream.close();
            objectOutputStream.close();
            socket.close();
        }catch (Exception e){

        }
    }
}
