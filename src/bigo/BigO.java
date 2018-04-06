package bigo;

import javafx.application.Platform;

import java.io.File;
import java.util.*;

public class BigO {


    private int N = 0;
    private int n1 = 0;
    private int n2 = 0;
    private String[] navne = readFile();

    int k = 0;
    Map<Integer, Runnable> methods = new HashMap<>();
    private BigOPresenter bigOPresenter;

    public BigO(BigOPresenter bigOPresenter){
        this.bigOPresenter = bigOPresenter;
        methods.put(1, this::m1);
        methods.put(2, this::m2);
        methods.put(3, this::m3);
        methods.put(4, this::m4);
        methods.put(5, this::m5);
        methods.put(6, this::m6);
    }
    

    private void m1(){
        for (int i = 1; i <= N; i++) {
            k = k + 5; // tilfældig operation
        }
    }

    private void m2(){
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                k = k + i + j;
            }
        }
    }

    private void m3(){
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
            k = k + i + j; // hvor mange gange?
            }
        }
    }
    private void m4(){
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= 20; j++) {
            k = k + i + j;
            }
        }
    }

    private void m5(){
        String name = "aksel";
        int div = first ? 2 : 1; // Halv liste i første omgang. Hel liste i anden omgang.
        for (int i = 0; i < navne.length / div ; i++) {
            if(navne[i].equalsIgnoreCase(name)){
                System.out.println("found " + name);
                return;
            }
        }
        System.out.println("did not find " + name);
    }
    private void m6(){

        System.out.println("hello world");
    }

    private boolean first=false;

    public void runMethod(int key){
        System.out.println("called with key: " + key);
        if (methods.containsKey(key)) {
            TimeResult timeResult = new TimeResult();
            long start = System.nanoTime();
            N = n1;
            first = true;
            methods.get(key).run();
            timeResult.setT1(System.nanoTime() - start);
            start = System.nanoTime();
            N = n2;
            first = false;
            methods.get(key).run();
            timeResult.setT2(System.nanoTime() - start);
            Platform.runLater(() -> bigOPresenter.setTimeResult(timeResult));

        }
    }

    private String[] readFile(){
        try {
            Scanner sc = new Scanner(new File("navnerandom.txt"));
            List<String> lines = new ArrayList();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if(line.length() > 0) {
                    lines.add(line);
                }
            }
            sc.close();
            return lines.toArray(new String[0]);
        }catch (Exception e){

        }
        return null;
    }

    public void setN1(int n1) {
        this.n1 = n1;
    }

    public void setN2(int n2) {
        this.n2 = n2;
    }


}
