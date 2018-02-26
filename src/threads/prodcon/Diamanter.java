package threads.prodcon;

public class Diamanter {

    private int count = 0;

    public synchronized void inc(){
        try {
            while (count >= 5) {
                wait(); // vi venter OG lytter på notifyAll
            }
            count = count + 1;
            System.out.print("inc: "+count + " ");
            notifyAll(); // signalerer til Consumer, at der er én til diamant
        }catch (Exception e){
        }
    }

    public synchronized void dec(){
        try {
            while (count <= 0) {
                wait(); // vi venter OG lytter på notifyAll
            }
            count = count - 1;
            System.out.print("dec: "+count + " ");
            notifyAll(); // signalerer til Consumer, at der er én til diamant
        }catch (Exception e){
        }
    }
}
