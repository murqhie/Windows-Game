package Models.Objects;

import Models.DataStructures.Vector;
import Models.DataStructures.Timer;
import java.util.Random;

public class Kamikaze implements ICharacter{
    private Vector position;
    private Vector velocity;
    private int radius = 5;
    private Player player;
    private int Color = 200;
    private int movingSpeed = 8;
    private Timer attackTimer = new Timer(100,100);
    private int explosionRadius = radius * 12;
    private int explosionSpeed = 3;
    private boolean dead = false;
    public Kamikaze(int radius, Player player) {
        this.position = new Vector(new Random().nextInt(1000),new Random().nextInt(1000));
        this.radius = radius;
        this.player = player;
    }

    public void move() {
       Vector distance = player.getPosition().add(this.position.multiplicate(-1));
        this.velocity = distance
                .unit()
                .multiplicate(movingSpeed);
        this.position = this.position.add(this.velocity);
    }
    public void attack() {
        if (attackTimer.isUp()){
            if (this.radius <= explosionRadius) {
               this.radius += explosionSpeed; }
            else{
                dead = true;}
        }
        attackTimer.tick();
    }
    public void getsHit() {

    }
    public int getColor() {return this.Color;}
    public float getX() {return this.position.getX();}
    public float getY() {return this.position.getY();}
    public int getRadius() {return this.radius;}
    public boolean isDead() {return dead;}

}
