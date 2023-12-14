package Models.Objects;

import Models.DataStructures.Vector;

import java.util.ArrayList;

public class Projectile {
    private Vector position;
    private Vector velocity;
    private int radius = 4;
    private Window window;
    private int knockBack = 2;
    private boolean playerProjectile;
    private boolean collided = false;

    public Projectile(Vector position, Vector velocity, Window window, Boolean playerProjectile) {
        this.position = position;
        this.velocity = velocity;
        this.window = window;
        this.playerProjectile = playerProjectile;
    }
    public void isCollidingWithWindow(){
        if(     this.position.getX()-radius <= window.getPosition().getX() |
                this.position.getY()-radius <= window.getPosition().getY() |
                this.position.getX()+radius >= window.getPosition().getX() + window.getWidth() |
                this.position.getY()+radius >= window.getPosition().getY() + window.getHeight()
        ){
            if(playerProjectile){
                window.setAcceleration(velocity.unit().multiplicate(knockBack));}
            this.collided = true;
        }

    }

    public void collidesWithEnemy(ArrayList<Enemy> enemies){
        for (Enemy enemy : enemies) {
            float distance = enemy.getPosition().add(this.position.multiplicate(-1)).norm();
            if (distance < (enemy.getRadius() + this.radius)){
                enemy.getsHit();
                this.collided = true;
            }
        }
    }

    public void collidesWithPlayer(Player player){
            float distance = player.getPosition().add(this.position.multiplicate(-1)).norm();
            if (distance < (player.getRadius() + this.radius)){
                player.getsHit();
                this.collided = true;
        }
    }


    public void move() {
        this.position = this.position.add(this.velocity);
    }
    public float getX(){return position.getX();}
    public float getY(){return position.getY();}
    public int getRadius() {return radius;}
    public Window getWindow() {return window;}
    public Vector getVelocity() {return velocity;}
    public int getKnockBack() {return this.knockBack;}
    public boolean isPlayerProjectile() {return playerProjectile;}

    public boolean isCollided() {return collided;}
}
