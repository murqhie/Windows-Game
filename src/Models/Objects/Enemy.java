package Models.Objects;
import Models.DataStructures.Vector;

/**
 * Abstract class for characters that can damage the player
 * <p>
 * Example:<br>
 * AntiCursor antiCursor = new AntiCursor(position, player)<br>
 * enemies.add(antiCursor)<br>
 * for(enemy in enemies){enemy.move()}<br>
 */

public abstract class Enemy implements ICharacter{
    protected Vector position, velocity, distance;
    protected int radius, movingSpeed;
    protected Player player;
    protected boolean dead = false;

    /**
     * Super constructor for classes that extend from Enemy class
     * @param position Position of the enemy
     * @param radius radius of enemies hitbox
     * @param movingSpeed moving speed of the enemy
     * @param player Player Object
     */
    public Enemy(Vector position, int radius,int movingSpeed, Player player) {
        this.position = position;
        this.radius = radius;
        this.movingSpeed = movingSpeed;
        this.player = player;
    }

    /**
     * moves the enemy towards the player
     */
    public void move() {
        distance = player.getPosition().add(this.position.multiply(-1));
        this.velocity = distance.unit().multiply(movingSpeed);
        this.position = this.position.add(this.velocity);}

    /**
     * switches dead attribute to true
     */
    public void getsHit() {this.dead = true;}

    /**s
     * detects if enemy hitbox and player hitbox are intersecting
     */
    public void collidesWithPlayer(){
        float distance = player.getPosition().add(this.position.multiply(-1)).norm();
        if (distance < (player.getRadius() + this.radius)){player.getsHit();}}

    /**
     * Getter for enemy position
     * @return Position
     */
    public Vector getPosition() {return position;}

    /**
     * Getter for enemy x position
     * @return x position
     */
    public float getX() {return this.position.getX();}
    /**
     * Getter for enemy y position
     * @return y position
     */
    public float getY() {return this.position.getY();}

    /**
     * Getter for enemy hitbox radius
     * @return hitbox radius
     */
    public int getRadius() {return this.radius;}

    /**
     * Getter for isDead
     * @return isDeed
     */
    public boolean isDead() {return dead;}

    /**
     * Getter for distance vector to the player
     * @return distance vector
     */
    public Vector getDistance(){return distance;}}
