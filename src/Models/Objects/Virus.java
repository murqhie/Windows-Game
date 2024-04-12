package Models.Objects;

import Models.DataStructures.Timer;
import Models.DataStructures.Vector;

import java.util.ArrayList;

/**
 * Enemy type which is stationary, creates its own window and shoots projectiles in a circular motion
 * <p>
 * Example:<br>
 * Virus virus = new Virus(position, player)<br>
 * enemies.add(virus)<br>
 * windows.add(virus.getWindow())<br>
 * for(enemy in enemies){enemy.attack(projectiles)}
 */

public class Virus extends Enemy{
    private final Timer shootTimer = new Timer(10, 0); // (Delay, Timer)
    private Vector shootingDirection = new Vector(0,-1);
    private final Window window;

    /**
     * Constructor for virus
     * @param position virus position
     * @param player PLayer Object
     */
    public Virus(Vector position, Player player) {super(position, 50, 0, player);this.window= new Window(402, 600, new Vector( position.getX()- 300, position.getY()-201));}

    /**
     * creates projectiles that change their shooting direction by a certain degree
     * @param projectiles Projectile ArrayList
     */
    public void attack(ArrayList<Projectile> projectiles) {
        if(shootTimer.isUp()){
            shootingDirection = shootingDirection.rotate(10);
            int shootingSpeed = 5;
            projectiles.add(new Projectile(this.position,shootingDirection.unit().multiply(shootingSpeed), "virus", 4));
            projectiles.add(new Projectile(this.position,shootingDirection.unit().multiply(-shootingSpeed), "virus", 4));
            shootTimer.reset();}
        shootTimer.tick();}
    /**
     * Getter for virus window
     * @return virus window
     */
    public Window getWindow() {return window;}
}
