package threads.waitnotify;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue {

    private Queue<String> queue = new LinkedList<>();
    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(String element) throws InterruptedException {
        while(queue.size() == capacity) {
            wait();
        }
        queue.add(element);
        notify(); // notifyAll() for multiple producer/consumer threads
    }

    public synchronized String take() throws InterruptedException {
        while(queue.isEmpty()) {
            wait();
        }
        String item = queue.remove();
        notify(); // notifyAll() for multiple producer/consumer threads
        return item;
    }
}