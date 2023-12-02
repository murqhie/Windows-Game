package Models.Objects;

import Models.DataStructures.Vector;
import java.lang.Math;


public class Player {
    int width;
    int height;
    Vector velocity;
    Vector acceleration;
    Vector position;
    int jerk;
    float friction;
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
        this.velocity = this.velocity.add(this.acceleration);
        this.velocity = this.velocity.multiplicate(1-friction);
        this.position = this.position.add(this.velocity);

    }
    public void setAcceleration(String direction) {
        switch(direction){
            case "UP" -> {this.acceleration.setY(-this.jerk);}
            case "LEFT" -> {this.acceleration.setX(-this.jerk);}
            case "DOWN" -> {this.acceleration.setY(this.jerk);}
            case "RIGHT" -> {this.acceleration.setX(this.jerk);}
            case "XZERO" -> {this.acceleration.setX(0);}
            case "YZERO" -> {this.acceleration.setY(0);}
        }
    }

    public Vector getPosition() {return position;}
    public float getX(){return position.getX();}
    public float getY(){return position.getY();}
    public int getWidth() {return width;}
    public int getHeight() {return height;}


}
