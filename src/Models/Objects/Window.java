package Models.Objects;

import Models.DataStructures.Vector;

public class Window {
    private int height;
    private int width;
    private int screenWidth;
    private int screenHeight;
    private int color = 100;
    private Vector position;


    public int getHeight() {return height;}
    public int getWidth() {return width;}
    public Vector getPosition() {return position;}
    public void setHeight(int height) {this.height = height;}
    public void setWidth(int width) {this.width = width;}
    public void setScreenWidth(int screenWidth) {this.screenWidth = screenWidth;}
    public void setScreenHeight(int screenHeight) {this.screenHeight = screenHeight;}
    public void setPosition(Vector position) {
        this.position = position;
        if(this.position.getX() < 0){this.position.setX(0);}
        if(this.position.getX() > screenWidth- width){this.position.setX(screenWidth-width);}
        if(this.position.getY() < 0){this.position.setY(0);}
        if(this.position.getY() > screenHeight-height){this.position.setY(screenHeight-height);}
    }
    public int getColor() {return color;}
}
