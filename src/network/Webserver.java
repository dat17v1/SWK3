package network;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Webserver {

    /*
    1. Forb
    2. Modtage req
    3. Parse
    4. Læs fil
    5. Send fil
    6. Luk
     */

    static String path = System.getProperty("user.dir") + "/"; // giver roden til projektmappen
    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(6500);
            while (true){
                System.out.println("server er startet");
                Socket socket = serverSocket.accept();
                System.out.println("forbindelse oprettet");
                Scanner requestScanner = new Scanner(socket.getInputStream());
                String requestLine = requestScanner.nextLine();
                StringTokenizer stringTokenizer = new StringTokenizer(requestLine);
                String fileName = "";
                DataOutputStream DOS = new DataOutputStream(socket.getOutputStream());
                if(stringTokenizer.nextToken().equals("GET")){
                    String fileString = stringTokenizer.nextToken();
                    if(fileString.startsWith("/")){
                        fileName = path + fileString.substring(1);
                    }
                    if(fileString.endsWith("/")){
                        fileName = path + "index.html";
                    }
                    File file = new File(fileName);
                    if(!file.isFile()){
                        System.out.println("bad request");
                        fileName = path + "error404.html";
                    }
                    int numBytes = (int)file.length(); // caster til int
                    byte[] fileArray = new byte[numBytes];
                    FileInputStream fileInputStream = new FileInputStream(file);
                    fileInputStream.read(fileArray, 0, numBytes);
                    fileInputStream.close();
                    DOS.writeBytes("HTTP/1.0 200 OK\r\n");
                    if(fileName.endsWith(".jpg")){
                        DOS.writeBytes("Content-Type:image/jpeg\r\n");
                    }
                    if(fileName.endsWith(".gif")){
                        DOS.writeBytes("Content-Type:image/gif\r\n");
                    }
                    DOS.writeBytes("Content-Length:"+numBytes+"\r\n");
                    DOS.writeBytes("\r\n");// er et krav fra protokollen
                    DOS.write(fileArray,0,numBytes); // her sendes filen
                    DOS.writeBytes("\r\n");


                }else {
                    System.out.println("bad request (not a GET)");
                    DOS.writeBytes("HTTP/1.0 400 unknown request, what are you doing?");
                    DOS.writeBytes("\r\n");
                }
                DOS.close();
                socket.close();
            }
        }catch (Exception e){

        }

    }




}
