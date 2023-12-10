package Models.Objects;

import Models.DataStructures.Vector;

public class Projectile {
    private Vector position;
    private Vector velocity;
    private int radius = 4;
    private Window window;
    private int knockBack = 2;
    private boolean playerProjectile;

    public Projectile(Vector position, Vector velocity, Window window, Boolean playerProjectile) {
        this.position = position;
        this.velocity = velocity;
        this.window = window;
        this.playerProjectile = playerProjectile;
    }
    public boolean isCollidingWithWindow(){
        if(this.position.getX()-radius <= window.getPosition().getX()){return true;}
        if(this.position.getY()-radius <= window.getPosition().getY()){return true;}
        if(this.position.getX()+radius >= window.getPosition().getX() + window.getWidth()){return true;}
        if(this.position.getY()+radius >= window.getPosition().getY() + window.getHeight()){return true;}
        return false;
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
}
