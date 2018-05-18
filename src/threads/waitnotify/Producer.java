package threads.waitnotify;

public class Producer implements Runnable {

    private BlockingQueue queue;
    private int counter=0;
    public Producer(BlockingQueue queue){
        this.queue = queue;
    }
    @Override
    public void run() {

        while(true){
            try {
                queue.put("greeting " + counter);
                counter++;
                System.out.println("Tilføjet " + counter);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
