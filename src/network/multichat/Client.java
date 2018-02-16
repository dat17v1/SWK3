package network.multichat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        try{
            InetAddress inetAddress = InetAddress.getByName("localhost");
            Socket socket = new Socket(inetAddress, 6780);
            System.out.println("oprettet forbindelse til server");
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader buffServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader buffKey = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                if(buffServer.ready()){
                    System.out.println("from server: " + buffServer.readLine());
                }
                if(buffKey.ready()){
                    printWriter.println(buffKey.readLine());
                }
                Thread.sleep(200);
            }
        }catch (Exception e){

        }
    }
}









