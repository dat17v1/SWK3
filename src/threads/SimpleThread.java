package threads;

public class SimpleThread implements Runnable{

// ændret

    @Override
    public void run() {
        System.out.println("tråd kører " + this.toString());
    }  //

}
