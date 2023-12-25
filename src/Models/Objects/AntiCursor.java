package Models.Objects;
import Models.DataStructures.Vector;
import Models.DataStructures.Timer;
import java.util.ArrayList;
public class AntiCursor extends Enemy{
    private Timer attackTimer = new Timer(100,100);
    private int explosionRadius = radius * 6;
    private int explosionSpeed = 3;
    public AntiCursor(Vector position, Player player, Window window) {
        super(position,10,8,player, window);
    }
    public void attack(ArrayList<Projectile> projectiles) {
        if (attackTimer.isUp()){
            if (this.radius <= explosionRadius) {this.radius += explosionSpeed;}
            else{dead = true;}}
        attackTimer.tick();}}
