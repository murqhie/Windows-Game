package Models.Objects;
import Models.DataStructures.Vector;
import java.util.ArrayList;

public class Bug extends Enemy {
    public Bug(Vector position, Player player, Window window) {
        super(position,20,2,player, window);
    }
    public void attack(ArrayList<Projectile> projectiles) {
        if(this.isDead()){projectiles.add(new Projectile(this.position,new Vector(0,0), false, 40));}}
    public Vector getDistance(){return distance;}
}
