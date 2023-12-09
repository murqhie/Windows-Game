package Models.DataStructures;

public class Timer {
    int rate;
    int counter;
    public Timer(int rate, int counter) {
        this.rate = rate;
        this.counter = counter;
    }
    public void tick(){this.counter--;}
    public void reset(){this.counter = this.rate;}
    public boolean isUp(){return counter <= 0;}
}
