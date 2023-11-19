package Models.Objects;

import Models.DataStructures.Vector;
import java.lang.Math;


public class Player {
    int width;
    int height;
    Vector velocity;
    Vector acceleration;
    Vector position;
    public Player(int width, int height, Vector position) {
        this.width = width;
        this.height = height;
        this.position = position;
        this.velocity = new Vector(-10,5);
        this.acceleration = new Vector(0,0);

    }

    public void move(){
        float t = 1f; // Zeitschritt
        float c = 0.01f; // Luftwiderstandskonstante
        this.position = this.position.add(this.velocity.multiplicate((float) ((0.5) * Math.pow(t,2)))); // s_t = 1/2 * v * t^2 + s_0

        // Add Beschleunigung
        this.velocity = this.velocity.add(this.acceleration.multiplicate(t));
        // Add Luftwiederstand
        this.velocity = this.velocity.add(this.velocity.unit().multiplicate((float) (-c*Math.pow(this.velocity.norm(),2)))); // a_t = -c * v^2 + v_0 (Luftwiderstand)

        if (this.velocity.norm() <= 1){
            this.velocity.setX(0);
            this.velocity.setY(0);
        }
    }

    public void setAcceleration(String direction) {
        switch(direction){
            case "UP" -> {}
            case "LEFT" -> {}
            case "DOWN" -> {}
            case "RIGHT" -> {}
        }

    }

    public Vector getPosition() {return position;}
    public float getX(){return position.getX();}
    public float getY(){return position.getY();}
    public int getWidth() {return width;}
    public int getHeight() {return height;}


}
