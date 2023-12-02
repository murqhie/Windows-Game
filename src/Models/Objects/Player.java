package Models.Objects;

import Models.DataStructures.Vector;
import java.lang.Math;


public class Player {
    int width;
    int height;
    Vector velocity;
    Vector acceleration;
    Vector position;
    float jerk;
    float friction;
    boolean[] keyInputs = new boolean[]{false,false,false,false}; // {w,a,s,d}
    public Player(int width, int height, Vector position) {
        this.width = width;
        this.height = height;
        this.position = position;
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

    public Vector getPosition() {return position;}
    public float getX(){return position.getX();}
    public float getY(){return position.getY();}
    public int getWidth() {return width;}
    public int getHeight() {return height;}





}
