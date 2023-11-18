package Models.Objects;

import Models.DataStructures.Point;

public class Player {
    int width;
    int height;
    Point position;
    public Player(int width, int height, Point position) {
        this.width = width;
        this.height = height;
        this.position = position;
    }

    public Point getPosition() {return position;}
    public int getX(){return position.getX();}
    public int getY(){return position.getY();}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
}
