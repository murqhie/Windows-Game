package Models.Objects;

import Models.DataStructures.Timer;
import Models.DataStructures.Vector;

import java.util.ArrayList;


public class Player implements ICharacter {
    private int radius, shootingSpeed = 15;
    private final Window window;
    private Vector position,shootDirection,velocity= new Vector(0,0),acceleration = new Vector(0,0);
    private float jerk = 1, friction  = 0.1f;
    private boolean[] keyInputs = new boolean[]{false,false,false,false}; // {w,a,s,d}
    private boolean mouseInput = false,dead = false;
    private final Timer shootTimer = new Timer(10, 0); // (Delay, Timer)
    // TODO Idee: ab einem bestimmten highscore unlockt man stärkere projektile
    public Player(int radius, Window window) {
        this.radius = radius;
        this.window = window;
        this.position = new Vector( (this.window.getPosition().getX() + (float) this.window.getWidth() /2) ,(this.window.getPosition().getY() + (float) this.window.getHeight() /2));}
    public void move(){
        this.setAcceleration();
        this.velocity = this.velocity.add(this.acceleration).multiply(1-friction);
        this.position = this.position.add(this.velocity);}
    public void attack(ArrayList<Projectile> projectiles){
        if(shootTimer.isUp() & mouseInput){
        projectiles.add(new Projectile(this.position,shootDirection.unit().multiply(shootingSpeed), "player", 4));
        shootTimer.reset();}
        shootTimer.tick();}
    public void getsHit() {this.dead = true;}
    public boolean isCollidingWithWindow(){return this.position.getX() - radius <= window.getPosition().getX() || this.position.getY() - radius <= window.getPosition().getY() || this.position.getX() + radius >= window.getPosition().getX() + window.getWidth() || this.position.getY() + radius >= window.getPosition().getY() + window.getHeight();}
    public void setAcceleration() {
        if(keyInputs[0] || keyInputs[2]){this.acceleration.setY(keyInputs[0] ? -this.jerk : this.jerk);}
        if(keyInputs[1] || keyInputs[3]){this.acceleration.setX(keyInputs[1] ? -this.jerk : this.jerk);}
        if((keyInputs[1] && keyInputs[3]) || (!keyInputs[1] && !keyInputs[3])){this.acceleration.setX(0);}
        if((keyInputs[0] && keyInputs[2]) || (!keyInputs[0] && !keyInputs[2])){this.acceleration.setY(0);}}
    public void setKeyInputs(int index, boolean value) {this.keyInputs[index] = value;}
    public float getX(){return position.getX();}
    public float getY(){return position.getY();}
    public Vector getPosition() {return position;}
    public int getRadius() {return radius;}
    public boolean isDead() {return dead;}
    public void setMouseInput(boolean mouseInput) {this.mouseInput = mouseInput;}
    public void setShootDirection(Vector shootDirection) {this.shootDirection = shootDirection;}
}
