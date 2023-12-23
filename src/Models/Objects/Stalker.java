package Models.Objects;

import Models.DataStructures.Timer;
import Models.DataStructures.Vector;
import java.util.ArrayList;

public class Stalker extends Enemy {
    private Timer shootTimer = new Timer(50, 0); // (Delay, Timer)
    private int shootingSpeed = 10;
    public Stalker( Vector position, Player player, Window window) {
        super(position,20,3,player, window);
    }
    public void attack(ArrayList<Projectile> projectiles) {
        if(shootTimer.isUp()){
            projectiles.add(new Projectile(this.position,distance.unit().multiplicate(shootingSpeed), false));
            shootTimer.reset();
        }
        shootTimer.tick();
    }

}
