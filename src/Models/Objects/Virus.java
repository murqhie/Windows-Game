package Models.Objects;

import Models.DataStructures.Timer;
import Models.DataStructures.Vector;

import java.util.ArrayList;

public class Virus extends Enemy{
    private Timer shootTimer = new Timer(10, 0); // (Delay, Timer)
    private int shootingSpeed = 5;
    private Vector shootingDirection = new Vector(0,-1);
    public Virus(Vector position, Player player, Window window) {super(position, 50, 0, player, window);}
    public void attack(ArrayList<Projectile> projectiles) {
        if(shootTimer.isUp()){
            shootingDirection = shootingDirection.rotate(10);
            projectiles.add(new Projectile(this.position,shootingDirection.unit().multiply(shootingSpeed), false));
            shootTimer.reset();}
        shootTimer.tick();}
    @Override
    public void getsHit() {
        this.dead = true;
    }
    public Window getWindow() {return window;}
}
