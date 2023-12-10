package Models.Objects;

import Models.DataStructures.Timer;
import Models.DataStructures.Vector;
import java.util.ArrayList;

public class Stalker extends Enemy {
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private Timer shootTimer = new Timer(50, 0); // (Delay, Timer)
    private int shootingSpeed = 5;


    public Stalker( Vector position, Player player, Window window) {
        super(position,30,3,player, window);
    }
    public void attack() {
        if(shootTimer.isUp()){
            projectiles.add(new Projectile(this.position,distance.unit().multiplicate(shootingSpeed),this.window, false));
            shootTimer.reset();
        }
        shootTimer.tick();
    }

    public ArrayList<Projectile> getProjectiles() {return projectiles;}

}
