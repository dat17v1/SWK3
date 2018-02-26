package threads.prodcon;

import java.util.Random;

public class Consumer implements Runnable{
    private Diamanter diamanter;

    public Consumer(Diamanter diamanter) {
       this.diamanter = diamanter;
    }

    @Override
    public void run() {
        while(true){
            diamanter.dec();
            System.out.println("Har taget en diamant");
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(1000));
            }catch (Exception e){

            }
        }
    }
}
