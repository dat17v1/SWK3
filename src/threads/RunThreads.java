package threads;

public class RunThreads {


    public static void main(String[] args) {
        SimpleThread simpleThread = new SimpleThread();
        SimpleThread simpleThread2 = new SimpleThread();
       // simpleThread.run(); // duer ikke
        Thread thread = new Thread(simpleThread);
        thread.start();

        Thread thread2 = new Thread(simpleThread2);
        thread2.start();
    }
}
