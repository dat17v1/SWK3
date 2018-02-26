package threads.prodcon;

import java.util.Random;

public class Producer implements Runnable {

    private Diamanter diamanter;

    public Producer(Diamanter diamanter) {
        this.diamanter = diamanter;
    }

    @Override
    public void run() {
        while(true){
            diamanter.inc();
            System.out.println("Har tilføjet en diamant");
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(1000));
            }catch (Exception e){

            }
        }
        // 1. tilføj en diamant, hvis der færre end 5,

        //      2.udskriv "Har tilføjet en diamant"
        //      3. og Sleep derefter 500 ms

        // 4. vent, hvis der ikke er nogen
    }
}
