package threads.prodcon;

public class Diamanter {

    private int count = 0;

    public synchronized void inc(){
        try {
            while (count >= 5) {
                wait(); // vi venter OG lytter på notifyAll
            }
            count = count + 1;
        }catch (Exception e){

        }
    }

    public synchronized void dec(){

        count = count - 1;
    }
}
