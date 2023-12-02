package Models.Objects;

import Models.DataStructures.Vector;

public class Window {
    private int height;
    private int width;
    private Vector position;


    public int getHeight() {return height;}
    public int getWidth() {return width;}
    public Vector getPosition() {return position;}
    public void setHeight(int height) {this.height = height;}
    public void setWidth(int width) {this.width = width;}
    public void setPosition(Vector position) {this.position = position;}
}
