package Models.Objects;

import Models.DataStructures.Vector;
import Models.DataStructures.Timer;

import java.util.Collection;
import java.util.Random;

public class Kamikaze extends Enemy{
    private Timer attackTimer = new Timer(100,100);
    private int explosionRadius = radius * 6;
    private int explosionSpeed = 3;

    public Kamikaze( Vector position, Player player, Window window) {
        super(position,10,8,player, window);
    }
    public void attack() {
        if (attackTimer.isUp()){
            if (this.radius <= explosionRadius) {
               this.radius += explosionSpeed; }
            else{
                dead = true;}
        }
        attackTimer.tick();
    }
    public Collection<Projectile> getProjectiles() {return null;}

}
