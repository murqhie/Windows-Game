package Models.Objects;

import Models.DataStructures.Timer;
import Models.DataStructures.Vector;
import java.util.ArrayList;

public class Stalker implements ICharacter {
    private Vector position;
    private Vector velocity;
    private int radius;
    private Player player;
    private Window window;
    private int Color = 250;
    private int movingSpeed = 2;
    private boolean dead = false;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private Timer shootTimer = new Timer(50, 0); // (Delay, Timer)
    private int shootingSpeed = 5;
    Vector distance;

    public Stalker(int radius, Player player, Vector position, Window window ) {
        this.position = position;
        this.radius = radius;
        this.player = player;
        this.window = window;
    }
    public void move() {
        distance = player.getPosition().add(this.position.multiplicate(-1));
        this.velocity = distance
                .unit()
                .multiplicate(movingSpeed);
        this.position = this.position.add(this.velocity);
    }
    public void attack() {
        if(shootTimer.isUp()){
            projectiles.add(new Projectile(this.position,distance.unit().multiplicate(shootingSpeed),this.window));
            shootTimer.reset();
        }
        shootTimer.tick();
    }
    public void getsHit() {




    }
    public int getColor() {return this.Color;}
    public float getX() {return this.position.getX();}
    public float getY() {return this.position.getY();}
    public int getRadius() {return this.radius;}
    public ArrayList<Projectile> getProjectiles() {return projectiles;}
    public boolean isDead() {return dead;}
}
