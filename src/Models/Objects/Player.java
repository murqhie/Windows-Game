package Models.Objects;

import Models.DataStructures.Timer;
import Models.DataStructures.Vector;

import java.util.ArrayList;


public class Player implements ICharacter {
    private int color = 255;
    private int radius;
    private int life = 500;
    private Window window;
    private Vector position;
    private Vector velocity = new Vector(0,0);
    private Vector acceleration = new Vector(0,0);
    private float jerk = 1;
    private float friction  = 0.1f;
    private boolean[] keyInputs = new boolean[]{false,false,false,false}; // {w,a,s,d}
    private boolean mouseInput = false;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private Timer shootTimer = new Timer(10, 0); // (Delay, Timer)
    private int shootingSpeed = 15;
    private Vector shootDirection;
    private boolean dead = false;
    public Player(int radius, Window window) {
        this.radius = radius;
        this.window = window;
        this.position = new Vector((float) (this.window.getPosition().getX() + this.window.getWidth() /2) , (float) (this.window.getPosition().getY() + this.window.getHeight() /2)  );
    }
    public void move(){
        this.setAcceleration();
        this.velocity = this.velocity.add(this.acceleration);
        this.velocity = this.velocity.multiplicate(1-friction);
        this.position = this.position.add(this.velocity);
    }
    public void attack(){
        if(shootTimer.isUp() & mouseInput){
        projectiles.add(new Projectile(this.position,shootDirection.unit().multiplicate(shootingSpeed), true));
        shootTimer.reset();
        }
        shootTimer.tick();
    }
    public void getsHit() {
        this.dead = true;
    }

    public boolean isCollidingWithWindow(){
        if(this.position.getX()-radius <= window.getPosition().getX()){return true;}
        if(this.position.getY()-radius <= window.getPosition().getY()){return true;}
        if(this.position.getX()+radius >= window.getPosition().getX() + window.getWidth()){return true;}
        if(this.position.getY()+radius >= window.getPosition().getY() + window.getHeight()){return true;}
        return false;
    }
    public void setAcceleration() {
            if(keyInputs[0]){this.acceleration.setY(-this.jerk);}
            if(keyInputs[1]){this.acceleration.setX(-this.jerk);}
            if(keyInputs[2]){this.acceleration.setY(this.jerk);}
            if(keyInputs[3]){this.acceleration.setX(this.jerk);}
            if((keyInputs[1] & keyInputs[3]) | (!keyInputs[1] & !keyInputs[3])){this.acceleration.setX(0);}
            if((keyInputs[0] & keyInputs[2]) | (!keyInputs[0] & !keyInputs[2])){this.acceleration.setY(0);}
        }
    public void setKeyInputs(int index, boolean value) {
        this.keyInputs[index] = value;
    }
    public int getColor() {return color;}
    public float getX(){return position.getX();}
    public float getY(){return position.getY();}
    public Vector getPosition() {return position;}
    public int getRadius() {return radius;}
    public boolean isDead() {return dead;}
    public ArrayList<Projectile> getProjectiles() {return projectiles;}
    public void setMouseInput(boolean mouseInput) {this.mouseInput = mouseInput;}
    public void setShootDirection(Vector shootDirection) {this.shootDirection = shootDirection;}
    public boolean isInWindow() {return true;}
}
