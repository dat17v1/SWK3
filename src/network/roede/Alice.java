package network.roede;

import java.io.File;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Alice {
    static String path = System.getProperty("user.dir")+"/";
    public static void main(String[] args)
    {
     try{
         System.out.println(path);
         File file = new File(path + "Alice.txt");
         if(file.isFile()){
             Scanner scanner = new Scanner(file);
             // iterere over filen én gang
             Map<Character, Integer> map = new TreeMap<>();
             for (int i = 97; i < 123 ; i++) {
                 map.put((char)i,0); // initialisér map med alfabet
             }
             String line = "";
             while(scanner.hasNextLine()){
             System.out.println(16);
                 line = scanner.nextLine().toLowerCase();
                 for (Character c : line.toCharArray()) {
                     if(Character.isLetter(c)) {
                         map.put(c, map.get(c) + 1);
                     }
                 }
             }
             System.out.println(map);

         }else {
             System.out.println("file not found");
         }
     }catch (Exception e){
         System.out.println(e.getMessage());
     }

    }

}
