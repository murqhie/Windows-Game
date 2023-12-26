package Models.Objects;
import Models.DataStructures.Vector;
import Models.DataStructures.Timer;
import java.util.ArrayList;
public class AntiCursor extends Enemy{
    private Timer attackTimer = new Timer(200,200);
    private boolean hasTrashBin;
    public AntiCursor(Vector position, Player player, Window window) {
        super(position,35,8,player, window);
    }
    public void attack(ArrayList<Projectile> projectiles) {if (attackTimer.isUp()){dead = true;} if (hasTrashBin){attackTimer.tick();}}
    public void move(){
        distance = new Vector(65,220).add(this.position.multiply(-1));
        if (hasTrashBin){distance = player.getPosition().add(this.position.multiply(-1));}
        if (distance.norm() <= 10){hasTrashBin = true;}
        this.velocity = distance.unit().multiply(movingSpeed);
        this.position = this.position.add(this.velocity);}
    public boolean hasTrashBin() {return hasTrashBin;}
}
