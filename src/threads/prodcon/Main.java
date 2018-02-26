package threads.prodcon;

public class Main {

    public static void main(String[] args) {
        Diamanter diamanter = new Diamanter();
        Consumer consumer = new Consumer(diamanter);
    }
}
