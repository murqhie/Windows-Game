package Models.Objects;

import Models.DataStructures.Timer;
import Models.DataStructures.Vector;

import java.util.ArrayList;

/**
 * Character which the player can control<br>
 * Dies when moving outside its own window or getting hit by an enemy or its projectiles<br>
 * Can attack by shooting projectiles towards the mouse position
 * <p>
 * Example:<br>
 * Player player = new Player(30,mainWindow)<br>
 * player.getsHit()<br>
 * checkGameOver()<br>
 * >>> GAME_OVER
 */
public class Player implements ICharacter {
    private int radius, shootingSpeed = 15;
    private final Window window;
    private Vector position,shootDirection,velocity= new Vector(0,0),acceleration = new Vector(0,0);
    private float jerk = 1, friction  = 0.1f;
    private boolean[] keyInputs = new boolean[]{false,false,false,false}; // {w,a,s,d}
    private boolean mouseInput = false,dead = false;
    private final Timer shootTimer = new Timer(10, 0); // (Delay, Timer)

    /**
     * Constructor for Player
     * @param radius hitbox radius
     * @param window main window
     */
    public Player(int radius, Window window) {
        this.radius = radius;
        this.window = window;
        this.position = new Vector( (this.window.getPosition().getX() + (float) this.window.getWidth() /2) ,(this.window.getPosition().getY() + (float) this.window.getHeight() /2));}

    /**
     * moves the player depending on its acceleration
     */
    public void move(){
        this.setAcceleration();
        this.velocity = this.velocity.add(this.acceleration).multiply(1-friction);
        this.position = this.position.add(this.velocity);}

    /**
     * shoots projectiles towards the shoot direction
     * @param projectiles Projectiles ArrayList
     */
    public void attack(ArrayList<Projectile> projectiles){
        if(shootTimer.isUp() & mouseInput){
        projectiles.add(new Projectile(this.position,shootDirection.unit().multiply(shootingSpeed), "player", 4));
        shootTimer.reset();}
        shootTimer.tick();}

    /**
     * switches dead attribute to true
     */
    public void getsHit() {this.dead = true;}

    /**
     * checks if player hitbox is colliding with its window walls
     * @return boolean if hitbox is colliding
     */
    public boolean isCollidingWithWindow(){return this.position.getX() - radius <= window.getPosition().getX() || this.position.getY() - radius <= window.getPosition().getY() || this.position.getX() + radius >= window.getPosition().getX() + window.getWidth() || this.position.getY() + radius >= window.getPosition().getY() + window.getHeight();}

    /**
     * sets acceleration depending on key inputs given by controller
     */
    public void setAcceleration() {
        if(keyInputs[0] || keyInputs[2]){this.acceleration.setY(keyInputs[0] ? -this.jerk : this.jerk);}
        if(keyInputs[1] || keyInputs[3]){this.acceleration.setX(keyInputs[1] ? -this.jerk : this.jerk);}
        if((keyInputs[1] && keyInputs[3]) || (!keyInputs[1] && !keyInputs[3])){this.acceleration.setX(0);}
        if((keyInputs[0] && keyInputs[2]) || (!keyInputs[0] && !keyInputs[2])){this.acceleration.setY(0);}}

    /**
     * used by controller to forward key input information by view
     * @param index 0 = w, 1 = a, 2 = s, 3 = d
     * @param value boolean if key is pressed
     */
    public void setKeyInputs(int index, boolean value) {this.keyInputs[index] = value;}

    /**
     * getter for player x position
     * @return
     */
    public float getX(){return position.getX();}

    /**
     * getter for player y position
     * @return
     */
    public float getY(){return position.getY();}

    /**
     * getter got player position
     * @return
     */
    public Vector getPosition() {return position;}

    /**
     * getter for hitbox radius
     * @return
     */
    public int getRadius() {return radius;}

    /**
     * getter for isDead boolean
     * @return
     */
    public boolean isDead() {return dead;}

    /**
     * setter for mouseInput boolean
     * @param mouseInput boolean
     */
    public void setMouseInput(boolean mouseInput) {this.mouseInput = mouseInput;}

    /**
     * determines what directions the projectiles should move
     * @param shootDirection Direction
     */
    public void setShootDirection(Vector shootDirection) {this.shootDirection = shootDirection;}
}
