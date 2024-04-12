package Models.Objects;

import Models.DataStructures.Vector;

/**
 * Area in which the projectiles and player can survive. <br>
 * In case a window is the main Window it can be moved by players projectiles.
 * <p>
 * Example: <br>
 * Window mainWindow = new Window(400, 600, 1920,1080)
 * <p>
 * Player player = new Player(20, mainWindow)<br>
 * for(projectile in projectiles){<br>
 *     projectile.getsOutOfWindow(mainWindow, enemyWindows)<br>
 * }
 */

public class Window {
    private final int height;
    private final int width;
    private int screenWidth;
    private int screenHeight;
    private Vector position;
    private Vector velocity = new Vector(0,0);
    private Vector acceleration = new Vector(0,0);

    /**
     * Constructor for Window
     * @param height height of Window
     * @param width width of Window
     * @param screenWidth width of the application window
     * @param screenHeight height of the application window
     */

    public Window(int height, int width, int screenWidth, int screenHeight) {
        this.height = height;
        this.width = width;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.position = new Vector((float) (screenWidth /2-(width/2)),(float)(screenHeight /2-(height/2)));}

    /**
     * Constructor for Window
     * @param height height of Window
     * @param width width of Window
     * @param position position of Window
     */
    public Window(int height, int width, Vector position) {
        this.height = height;
        this.width = width;
        this.position = position;}

    /**
     * Getter for window position
     * @return position
     */
    public Vector getPosition() {return position;}

    /**
     * Getter for window height
     * @return height
     */
    public int getHeight() {return height;}

    /**
     * Getter for window width
     * @return width
     */
    public int getWidth() {return width;}

    /**
     * moves the window based on its acceleration
     */
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

    /**
     * sets the acceleration of the window
     * @param acceleration Acceleration Vector
     */
    public void setAcceleration(Vector acceleration) {this.acceleration = acceleration;}}
