package bigo;

public class TimeResult {
    private long t1 = 0;
    private long t2 = 0;

    public long getT1() {
        return t1;
    }

    public void setT1(long t1) {
        this.t1 = t1;
    }

    public long getT2() {
        return t2;
    }

    public void setT2(long t2) {
        this.t2 = t2;
    }

    public String toString(){
        return "t1: " + t1 + " t2: " + t2;
    }
}
