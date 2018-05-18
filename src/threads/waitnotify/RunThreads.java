package threads.waitnotify;

public class RunThreads {

    public static void main(String[] args) {
        BlockingQueue blockingQueue = new BlockingQueue(2);
        Producer producer = new Producer(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);
        producerThread.start();
        consumerThread.start();
    }
}
