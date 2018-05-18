package threads.waitnotify;

public class Consumer implements Runnable {

    private BlockingQueue queue;
    public Consumer(BlockingQueue queue){
        this.queue = queue;
    }
    @Override
    public void run() {

        while(true){
            try {
                System.out.println("hentet element: "+queue.take());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
