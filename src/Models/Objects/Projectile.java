package Models.Objects;

import Models.DataStructures.Vector;

import java.util.ArrayList;

public class Projectile {
    private Vector position;
    private Vector velocity;
    private int radius = 4;
    private int knockBack = 2;
    private boolean playerProjectile;
    private boolean collided = false;
    private Window window;
    public Projectile(Vector position, Vector velocity, Boolean playerProjectile) {
        this.position = position;
        this.velocity = velocity;
        this.playerProjectile = playerProjectile;}

    public Projectile(Vector position, Vector velocity, Boolean playerProjectile, int radius) {
        this.position = position;
        this.velocity = velocity;
        this.playerProjectile = playerProjectile;
        this.radius = radius;
    }
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

        if (playerProjectile & this.window== mainWindow) {mainWindow.setAcceleration(velocity.unit().multiply(knockBack));}}
    private boolean isInWindow(Window window){return  !collisionL( window) &  !collisionU( window) &  !collisionR( window) &  !collisionO( window);}
    private boolean collisionL( Window window){return this.position.getX() - radius <= window.getPosition().getX() + 10;}
    private boolean collisionO( Window window){return this.position.getY() - radius <= window.getPosition().getY() + 25;}
    private boolean collisionR( Window window){return this.position.getX() + radius >= window.getPosition().getX() + window.getWidth() - 10;}
    private boolean collisionU( Window window){return this.position.getY() + radius >= window.getPosition().getY() + window.getHeight() - 10;}
    private void collisionResolution(){
        if(this.window == null){return;}
        if(collisionL(this.window)){this.position.setX(window.getPosition().getX() + radius);}
        if(collisionR(this.window)){this.position.setX(window.getPosition().getX() + window.getWidth() - radius);}
        if(collisionU(this.window)){this.position.setY(window.getPosition().getY() + window.getHeight() - radius);}
        if(collisionO(this.window)){this.position.setY(window.getPosition().getY() + radius);}}
    public boolean collidesWithEnemy(ArrayList<Enemy> enemies){
        for (Enemy enemy : enemies) {
            if (enemy.getPosition().add(this.position.multiply(-1)).norm() < (enemy.getRadius() + this.radius)){
                enemy.getsHit();
                this.collided = true;
                return true;}}
        return false;
    }
    public void collidesWithPlayer(Player player){
            if (player.getPosition().add(this.position.multiply(-1)).norm() < (player.getRadius() + this.radius)){
                player.getsHit();
                this.collided = true;}}
    public void move() {this.position = this.position.add(this.velocity);}
    public float getX(){return position.getX();}
    public float getY(){return position.getY();}
    public int getRadius() {return radius;}
    public boolean isPlayerProjectile() {return playerProjectile;}
    public boolean isCollided() {return collided;}
}
