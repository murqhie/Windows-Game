package Models.DataStructures;

/**
 * Acts as a tick-based timer.
 * <p>
 * Example:<br>
 *  if(shootTimer.isUp()){<br>
 *        projectiles.add(new Projectile(position,shootingDirection, "virus", 4));<br>
 *        shootTimer.reset();<br>
 *        }<br>
 *  shootTimer.tick();<br>
 */
public class Timer {
    private int period, counter;

    /**
     * Constructor for the Timer.
     * @param period tick count on how long the timer should run.
     * @param counter determines on what tick count the Timer should initially start
     */
    public Timer(int period, int counter) {this.period = period;this.counter = counter; }

    /**
     * lowers the counter on each call by 1
     */
    public void tick(){this.counter--;}

    /**
     * resets the Timer
     */
    public void reset(){this.counter = this.period;}

    /**
     * tells if the Timer is up
     * @return Boolean isUp
     */
    public boolean isUp(){return counter <= 0;}

    /**
     * Setter for the period
     * @param period Tick-period
     */
    public void setPeriod(int period) {this.period = period;}
}
