package Models.Objects;

import Models.DataStructures.Vector;



public class Player {
    int radius;
    Window window;
    Vector velocity;
    Vector acceleration;
    Vector position;
    float jerk;
    float friction;
    boolean[] keyInputs = new boolean[]{false,false,false,false}; // {w,a,s,d}
    public Player(int radius, Window window) {
        this.radius = radius;
        this.window = window;
        this.position = new Vector((float) this.window.getWidth() /2-radius, (float) this.window.getHeight() /2-radius);
        this.velocity = new Vector(0,0);
        this.acceleration = new Vector(0,0);
        this.jerk = 1;
        this.friction = 0.1f;

    }
    public void move(){
        this.setAcceleration();
        this.velocity = this.velocity.add(this.acceleration);
        this.velocity = this.velocity.multiplicate(1-friction);
        this.position = this.position.add(this.velocity);


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
            if(!keyInputs[1] & !keyInputs[3]){this.acceleration.setX(0);}
            if(!keyInputs[0] & !keyInputs[2]){this.acceleration.setY(0);}
        }
    public void setKeyInputs(int index, boolean value) {
        this.keyInputs[index] = value;
    }
    public float getX(){return position.getX();}
    public float getY(){return position.getY();}
    public int getRadius() {return radius;}





}
