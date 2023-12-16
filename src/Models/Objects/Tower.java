package Models.Objects;

import Models.DataStructures.Timer;
import Models.DataStructures.Vector;

import java.util.ArrayList;

public class Tower extends Enemy{
    private Timer shootTimer = new Timer(50, 0); // (Delay, Timer)
    private int shootingSpeed = 5;
    public Tower(Vector position, Player player) {
        super(position, 50, 0, player, new Window(200,200, new Vector(position.getX()-100,position.getY()-100)));
    }
    public void attack(ArrayList<Projectile> projectiles) {
        if(shootTimer.isUp()){
            projectiles.add(new Projectile(this.position,distance.unit().multiplicate(shootingSpeed), false));
            shootTimer.reset();
        }
        shootTimer.tick();
    }
    @Override
    public void getsHit() {
        this.dead = true;
    }
    public Window getWindow() {return window;}
}
