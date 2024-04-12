package Models.Objects;

import Models.DataStructures.Vector;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Round hitbox which kills enemies or player on collision or can move the main window if parent is "player"
 * <p>
 * Example:<br>
 * ArrayList<Projectile> projectiles = new ArrayList<>();<br>
 * virus.attack()<br>
 * for(projectile in projectiles){<br>
 *     projectile.getsOutOfWindow(mainWindow, enemyWindows)<br>
 * }
 */

public class Projectile {
    private Vector position;
    private final Vector velocity;
    private final int radius;
    private final String parent;
    private boolean collided = false;
    private Window window;

    /**
     * Constructor for Projectile
     * @param position projectile position
     * @param velocity projectile velocity
     * @param parent String "player" or "enemy" to dictate its behavior
     * @param radius hitbox radius
     */
    public Projectile(Vector position, Vector velocity, String parent, int radius) {
        this.position = position;
        this.velocity = velocity;
        this.parent = parent;
        this.radius = radius;}

    /**
     * in case of collision, switches collided attribute to true and moves main window if parent was "player"
     * @param mainWindow main window object
     * @param enemyWindows enemy window ArrayList
     */
    public void getsOutOfWindow(Window mainWindow, ArrayList<Window> enemyWindows){

        if (isInWindow(mainWindow)){
            this.window = mainWindow;
            return;}

        this.collided = true;
        for (Window enemyWindow : enemyWindows) {
            if(isInWindow(enemyWindow)){
                this.collided = false;
                this.window = enemyWindow;
                return;}}

        collisionResolution();

        if (Objects.equals(parent, "player") & this.window== mainWindow) {
            int knockBack = 2;
            mainWindow.setAcceleration(velocity.unit().multiply(knockBack));}}
    private boolean isInWindow(Window window){return  !collisionL( window) &  !collisionU( window) &  !collisionR( window) &  !collisionO( window);}
    private boolean collisionL( Window window){return this.position.getX() + radius <= window.getPosition().getX() + 10;}
    private boolean collisionO( Window window){return this.position.getY() + radius <= window.getPosition().getY() + 25;}
    private boolean collisionR( Window window){return this.position.getX() - radius >= window.getPosition().getX() + window.getWidth() - 10;}
    private boolean collisionU( Window window){return this.position.getY() - radius >= window.getPosition().getY() + window.getHeight() - 10;}
    private void collisionResolution(){
        if(this.window == null){return;}
        if(collisionL(this.window)){this.position.setX(window.getPosition().getX() + radius);}
        if(collisionR(this.window)){this.position.setX(window.getPosition().getX() + window.getWidth() - radius);}
        if(collisionU(this.window)){this.position.setY(window.getPosition().getY() + window.getHeight() - radius);}
        if(collisionO(this.window)){this.position.setY(window.getPosition().getY() + radius);}}

    /**
     * in case of collision, switches collided attribute and enemies isDead attribute to true
     * @param enemies Enemies ArrayList
     * @return boolean if projectile collided with enemy
     */
    public boolean collidesWithEnemy(ArrayList<Enemy> enemies){
        for (Enemy enemy : enemies) {
            if (enemy.getPosition().add(this.position.multiply(-1)).norm() < (enemy.getRadius() + this.radius)){
                enemy.getsHit();
                this.collided = true;
                return true;}}
        return false;
    }
    /**
     * in case of collision, switches collided attribute and players isDead attribute to true
     * @param player Player Object
     * @return boolean if projectile collided with player
     */
    public void collidesWithPlayer(Player player){
            if (player.getPosition().add(this.position.multiply(-1)).norm() < (player.getRadius() + this.radius)){
                player.getsHit();
                this.collided = true;}}

    /**
     * moves projectile depending on its velocity
     */
    public void move() {this.position = this.position.add(this.velocity);}

    /**
     * getter for projectiles x position
     * @return x position
     */
    public float getX(){return position.getX();}
    /**
     * getter for projectiles y position
     * @return y position
     */
    public float getY(){return position.getY();}

    /**
     * getter for hitbox radius
     * @return hitbox radius
     */
    public int getRadius() {return radius;}

    /**
     * getter for projectile parent
     * @return projectile parent String
     */
    public String getParent() {return parent;}

    /**
     * getter for isCollided attribute
     * @return
     */
    public boolean isCollided() {return collided;}
}
