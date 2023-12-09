package Models.Objects;

import Models.DataStructures.Vector;

public class Window {
    private int height;
    private int width;
    private int screenWidth;
    private int screenHeight;
    private int color = 100;
    private Vector position;
    Vector velocity = new Vector(0,0);
    Vector acceleration = new Vector(0,0);
    float friction  = 0.05f;

    public Window(int height, int width, int screenWidth, int screenHeight) {
        this.height = height;
        this.width = width;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.position = new Vector((float) (screenWidth /2-(width/2)),(float)(screenHeight /2-(height/2)));
    }

    public int getHeight() {return height;}
    public int getWidth() {return width;}
    public Vector getPosition() {return position;}
    public void setHeight(int height) {this.height = height;}
    public void setWidth(int width) {this.width = width;}
    public void setScreenWidth(int screenWidth) {this.screenWidth = screenWidth;}
    public void setScreenHeight(int screenHeight) {this.screenHeight = screenHeight;}
    public void move() {
        this.velocity = this.velocity.add(this.acceleration);
        this.velocity = this.velocity.multiplicate(1-friction);
        this.position = this.position.add(this.velocity);
        this.acceleration = new Vector(0,0);

        if(this.position.getX() < 0){this.position.setX(0);}
        if(this.position.getX() > screenWidth- width){this.position.setX(screenWidth-width);}
        if(this.position.getY() < 0){this.position.setY(0);}
        if(this.position.getY() > screenHeight-height){this.position.setY(screenHeight-height);}
    }
    public void setAcceleration(Vector acceleration) {this.acceleration = acceleration;}
    public void setPosition(Vector position) {this.position = position;}
    public int getColor() {return color;}
}
