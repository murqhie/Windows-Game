package Models.DataStructures;

/**
 * Two-dimensional Vector which can do operations such as addition, scalar multiplication and matrix multiplication with a rotation matrix
 * <p>
 * Example:<br>
 * projectiles.add(new Projectile(this.position,new Vector(0,-1).unit().multiply(5), "virus", 4));
 */
public class Vector {
    private float x, y;

    /**
     * Constructor for the vector
     * @param x X value
     * @param y Y value
     */
    public Vector(float x, float y) {this.x = x;this.y = y;}

    /**
     * vector addition
     * @param other vector
     * @return result vector
     */
    public Vector add(Vector other){return new Vector(this.x+other.getX(), this.y+other.getY());}
    //public Vector add(float number){   return new Vector(this.x+number, this.y+number);}

    /**
     * scalar multiplication
     * @param number scalar
     * @return scaled vector
     */
    public Vector multiply(float number){
        return new Vector(this.x*number, this.y*number);
    }

    /**
     * matrix multiplication with a rotation matrix for a specific rotation angle
     * @param angle in degrees
     * @return rotated vector
     */

    public Vector rotate(int angle){return new Vector((float) (Math.cos(Math.toRadians(angle)) * this.x - Math.sin(Math.toRadians(angle)) * this.y), (float) Math.sin(Math.toRadians(angle) * this.x + Math.cos(Math.toRadians(angle)) * this.y));}
    //public Vector pow(float number){return new Vector((float) Math.pow(this.x,number), (float) Math.pow(this.y,number));}

    /**
     * length of the vector
     * @return norm
     */
    public float norm(){return (float) Math.sqrt(Math.pow(this.x,2) + Math.pow(this.y,2));}

    /**
     * scales the vector to a unit vector
     * @return unit vector
     */
    public Vector unit(){if(this.norm() == 0){return new Vector(0,0);}return this.multiply(1/this.norm());}

    /**
     * getter for X
     * @return x value
     */
    public float getX() {return x;}

    /**
     * setter for Y
     * @param x value
     */
    public void setX(float x) {this.x = x;}
    /**
     * getter for Y
     * @return y value
     */
    public float getY() {return y;}
    /**
     * setter for Y
     * @param y value
     */
    public void setY(float y) {this.y = y;}}
