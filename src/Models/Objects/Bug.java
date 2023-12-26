package Models.Objects;

import Models.DataStructures.Timer;
import Models.DataStructures.Vector;
import java.util.ArrayList;

public class Bug extends Enemy {
    private Timer shootTimer = new Timer(120, 0); // (Delay, Timer)
    private int shootingSpeed = 0;
    public Bug(Vector position, Player player, Window window) {
        super(position,20,2,player, window);
    }
    public void attack(ArrayList<Projectile> projectiles) {
        if(shootTimer.isUp()){projectiles.add(new Projectile(this.position,new Vector(0,0), false, 40));
            shootTimer.reset();}shootTimer.tick();}
    public Vector getDistance(){return distance;}
}
