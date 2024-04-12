package Models.Objects;
import Models.DataStructures.Vector;
import java.util.ArrayList;

/**
 * Enemy type that follows player and creates a "graphic glitch hole" when shot that acts as a big stationary projectile.
 * <p>
 * Example:<br>
 * Bug bug = new Bug(position, player)<br>
 * enemies.add(bug)<br>
 * for(enemy in enemies){enemy.move()}<br>
 */
public class Bug extends Enemy {
    /**
     * Constructor for Bug
     * @param position Position of the Bug
     * @param player Player object
     */
    public Bug(Vector position, Player player) {
        super(position,20,2,player);
    }

    /**
     * creates a "graphic glitch hole"  when shot that acts as a big stationary projectile.
     * @param projectiles projectiles ArrayList
     */
    public void attack(ArrayList<Projectile> projectiles) {
        if(this.isDead()){projectiles.add(new Projectile(this.position,new Vector(0,0), "bug", 40));}}

}
