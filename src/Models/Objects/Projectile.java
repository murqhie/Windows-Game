package Models.Objects;

import Models.DataStructures.Vector;

public class Projectile {
    Vector position;
    Vector velocity;
    int radius;

    public Projectile(Vector position, Vector velocity) {
        this.position = position;
        this.velocity = velocity;
        this.radius = 4;
    }

    public void move() {
        this.position = this.position.add(this.velocity);
    }
    public float getX(){return position.getX();}
    public float getY(){return position.getY();}
    public int getRadius() {return radius;}
}
