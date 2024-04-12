package Models.Objects;
import Models.DataStructures.Vector;
import java.util.ArrayList;

/**
 * Enemy type which takes the trash bin from the desktop and chases the player.<br>
 * Does not shoot any projectiles and only damages the player by colliding into it.
 * <p>
 * Example:<br>
 * AntiCursor antiCursor = new AntiCursor(position, player)<br>
 * enemies.add(antiCursor)<br>
 * for(enemy in enemies){enemy.move()}
 */
public class AntiCursor extends Enemy{
    private boolean hasTrashBin;

    /**
     * Constructor for AntiCursor enemy
     * @param position Position of the AntiCursor
     * @param player Player object
     */
    public AntiCursor(Vector position, Player player) {
        super(position,35,8,player);
    }

    /**
     * Attack of AntiCursor<br>
     * must have an attack method because it implements ICharacter but is only enemy that cannot shoot projectiles which is why this method is empty.
     * @param projectiles Projectile ArrayList
     */
    public void attack(ArrayList<Projectile> projectiles) {}

    /**
     * makes the AntiCursor move<br>
     * first moves towards trash bin and then towards player
     */
    public void move(){
        distance = new Vector(65,220).add(this.position.multiply(-1));
        if (hasTrashBin){distance = player.getPosition().add(this.position.multiply(-1));}
        if (distance.norm() <= 10){hasTrashBin = true;}
        this.velocity = distance.unit().multiply(movingSpeed);
        this.position = this.position.add(this.velocity);}

    /**
     * Boolean whether the AntiCurse has the trash bin or not
     * @return boolean if AntiCursor has trash bin
     */
    public boolean hasTrashBin() {return hasTrashBin;}
}
