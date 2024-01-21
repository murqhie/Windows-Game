package Models.Objects;

import Models.DataStructures.Timer;
import Models.DataStructures.Vector;

import java.util.ArrayList;

public class Virus extends Enemy{
    private final Timer shootTimer = new Timer(10, 0); // (Delay, Timer)
    private Vector shootingDirection = new Vector(0,-1);
    public Virus(Vector position, Player player, Window window) {super(position, 50, 0, player);this.window= window;}
    public void attack(ArrayList<Projectile> projectiles) {
        if(shootTimer.isUp()){
            shootingDirection = shootingDirection.rotate(10);
            int shootingSpeed = 5;
            projectiles.add(new Projectile(this.position,shootingDirection.unit().multiply(shootingSpeed), "virus", 4));
            projectiles.add(new Projectile(this.position,shootingDirection.unit().multiply(-shootingSpeed), "virus", 4));
            shootTimer.reset();}
        shootTimer.tick();}
}
