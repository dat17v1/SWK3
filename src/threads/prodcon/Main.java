package threads.prodcon;

public class Main {

    public static void main(String[] args) {
        Diamanter diamanter = new Diamanter();
        Consumer consumer = new Consumer(diamanter);
        Producer producer = new Producer(diamanter);
        Thread conThread = new Thread(consumer);
        Thread prodThread = new Thread(producer);
        prodThread.start();
        conThread.start();
    }
}
