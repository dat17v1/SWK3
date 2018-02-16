package network.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

    public static void main(String[] args) {
        try{
            DatagramSocket dgs = new DatagramSocket();
            System.out.println("Client: dgs created...");
            byte[] sendArr = "Hello".getBytes();
            InetAddress address = InetAddress.getByName("192.168.0.2");
            int port = 6780;
            DatagramPacket datagramPacket = new DatagramPacket(sendArr, sendArr.length, address, port);
            dgs.send(datagramPacket);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
