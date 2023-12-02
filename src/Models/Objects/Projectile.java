package Models.Objects;

import Models.DataStructures.Vector;

public class Projectile {
    Vector position;
    Vector velocity;
    int radius;
    Window window;

    public Projectile(Vector position, Vector velocity, Window window) {
        this.position = position;
        this.velocity = velocity;
        this.window = window;
        this.radius = 4;
    }
    public boolean isCollidingWithWindow(){
        if(this.position.getX()-radius <= window.getPosition().getX()){return true;}
        if(this.position.getY()-radius <= window.getPosition().getY()){return true;}
        if(this.position.getX()+radius >= window.getPosition().getX() + window.getWidth()){return true;}
        if(this.position.getY()+radius >= window.getPosition().getY() + window.getHeight()){return true;}
        return false;
    }
    public String isCollidingWith(){
        if(this.position.getX()-radius <= window.getPosition().getX()){return "LEFT";}
        if(this.position.getY()-radius <= window.getPosition().getY()){return "UP";}
        if(this.position.getX()+radius >= window.getPosition().getX() + window.getWidth()){return "RIGHT";}
        if(this.position.getY()+radius >= window.getPosition().getY() + window.getHeight()){return "DOWN";}
        return null;
    }


    public void move() {
        this.position = this.position.add(this.velocity);
    }
    public float getX(){return position.getX();}
    public float getY(){return position.getY();}
    public int getRadius() {return radius;}
    public Window getWindow() {return window;}
}
