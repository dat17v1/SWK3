package network.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {

    public static void main(String[] args) {
        try{
            DatagramSocket dgs = new DatagramSocket(6780);
            System.out.println("DatagramSocket created...");
            byte[] receiveArr = new byte[1024];
            while(true) {
                DatagramPacket datagramPacket = new DatagramPacket(receiveArr, receiveArr.length);
                dgs.receive(datagramPacket);
                String message = new String(receiveArr, 0, datagramPacket.getLength());
                System.out.println("Received from 'client' " + message);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
