package Models.Objects;
import Models.DataStructures.Vector;
import java.util.ArrayList;
public class AntiCursor extends Enemy{
    private boolean hasTrashBin;
    public AntiCursor(Vector position, Player player) {
        super(position,35,8,player);
    }
    public void attack(ArrayList<Projectile> projectiles) {}
    public void move(){
        distance = new Vector(65,220).add(this.position.multiply(-1));
        if (hasTrashBin){distance = player.getPosition().add(this.position.multiply(-1));}
        if (distance.norm() <= 10){hasTrashBin = true;}
        this.velocity = distance.unit().multiply(movingSpeed);
        this.position = this.position.add(this.velocity);}
    public boolean hasTrashBin() {return hasTrashBin;}
}
