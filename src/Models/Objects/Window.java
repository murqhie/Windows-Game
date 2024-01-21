package Models.Objects;

import Models.DataStructures.Vector;

public class Window {
    private final int height;
    private final int width;
    private int screenWidth;
    private int screenHeight;
    private Vector position;
    private Vector velocity = new Vector(0,0);
    private Vector acceleration = new Vector(0,0);

    public Window(int height, int width, int screenWidth, int screenHeight) {
        this.height = height;
        this.width = width;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.position = new Vector((float) (screenWidth /2-(width/2)),(float)(screenHeight /2-(height/2)));}
    public Window(int height, int width, Vector position) {
        this.height = height;
        this.width = width;
        this.position = position;}
    public Vector getPosition() {return position;}
    public int getHeight() {return height;}
    public int getWidth() {return width;}
    public void move() {
        this.velocity = this.velocity.add(this.acceleration);
        float friction = 0.05f;
        this.velocity = this.velocity.multiply(1- friction);
        this.position = this.position.add(this.velocity);
        this.acceleration = new Vector(0,0);

        if(this.position.getX() < 0){this.position.setX(0);}
        if(this.position.getX() > screenWidth- width){this.position.setX(screenWidth-width);}
        if(this.position.getY() < 0){this.position.setY(0);}
        if(this.position.getY() > screenHeight-height - 54){this.position.setY(screenHeight-height -54);}}
    public void setAcceleration(Vector acceleration) {this.acceleration = acceleration;}}
