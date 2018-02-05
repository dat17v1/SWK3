package network;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class NanoTCPServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5555);
            Socket socket = serverSocket.accept(); // blokerer
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream, true);
            printWriter.println("Hello There!");

            Scanner scanner = new Scanner(socket.getInputStream());
            String message = scanner.nextLine();
            System.out.println(message);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
