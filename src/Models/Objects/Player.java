package Models.Objects;

import Models.DataStructures.Vector;

import java.util.ArrayList;


public class Player {
    int color = 255;
    int radius;
    Window window;
    Vector position;
    Vector velocity = new Vector(0,0);
    Vector acceleration = new Vector(0,0);
    float jerk = 1;
    float friction  = 0.1f;
    boolean[] keyInputs = new boolean[]{false,false,false,false}; // {w,a,s,d}
    boolean mouseInput = false;
    ArrayList<Projectile> projectiles = new ArrayList<>();
    Vector shootTimer = new Vector(10, 0); // (Delay, Timer)
    int shootingSpeed = 15;
    Vector shootDirection;
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
    public void shoot(){
        if(shootTimer.getY() <= 0 & mouseInput){
        projectiles.add(new Projectile(this.position,shootDirection.unit().multiplicate(shootingSpeed),this.window));
        shootTimer.setY(shootTimer.getX());
        }
        shootTimer.setY(shootTimer.getY()-1);
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
    public int getRadius() {return radius;}
    public ArrayList<Projectile> getProjectiles() {return projectiles;}
    public void setMouseInput(boolean mouseInput) {this.mouseInput = mouseInput;}

    public void setShootDirection(Vector shootDirection) {this.shootDirection = shootDirection;}
}
