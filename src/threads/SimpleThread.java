package threads;

public class SimpleThread implements Runnable{



    @Override
    public void run() {
        System.out.println("tråd kører " + this.toString());
    }  //

}
