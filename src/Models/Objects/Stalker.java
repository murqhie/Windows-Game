package Models.Objects;

import Models.DataStructures.Timer;
import Models.DataStructures.Vector;
import java.util.ArrayList;

public class Stalker extends Enemy {
    private Timer shootTimer = new Timer(50, 0); // (Delay, Timer)
    private int shootingSpeed = 5;
    public Stalker( Vector position, Player player, Window window) {
        super(position,20,3,player, window);
    }
    public void attack(ArrayList<Projectile> projectiles) {
        if(shootTimer.isUp()){
            projectiles.add(new Projectile(this.position,distance.unit().multiply(shootingSpeed), false));
            shootTimer.reset();}
        shootTimer.tick();}
    public Vector getDistance(){return distance;}
}
