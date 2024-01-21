package Models.Objects;
import Models.DataStructures.Vector;
import java.util.ArrayList;

public class Bug extends Enemy {
    public Bug(Vector position, Player player) {
        super(position,20,2,player);
    }
    public void attack(ArrayList<Projectile> projectiles) {
        if(this.isDead()){projectiles.add(new Projectile(this.position,new Vector(0,0), "bug", 40));}}
    public Vector getDistance(){return distance;}
}
