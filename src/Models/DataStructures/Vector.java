package Models.DataStructures;

public class Vector {
    private float x, y;
    public Vector(float x, float y) {this.x = x;this.y = y;}
    public Vector add(Vector other){return new Vector(this.x+other.getX(), this.y+other.getY());}
    //public Vector add(float number){   return new Vector(this.x+number, this.y+number);}
    public Vector multiply(float number){
        return new Vector(this.x*number, this.y*number);
    }
    public Vector rotate(int angle){return new Vector((float) (Math.cos(Math.toRadians(angle)) * this.x - Math.sin(Math.toRadians(angle)) * this.y), (float) Math.sin(Math.toRadians(angle) * this.x + Math.cos(Math.toRadians(angle)) * this.y));}
    //public Vector pow(float number){return new Vector((float) Math.pow(this.x,number), (float) Math.pow(this.y,number));}
    public float norm(){return (float) Math.sqrt(Math.pow(this.x,2) + Math.pow(this.y,2));}
    public Vector unit(){if(this.norm() == 0){return new Vector(0,0);}return this.multiply(1/this.norm());}
    public float getX() {return x;}
    public void setX(float x) {this.x = x;}
    public float getY() {return y;}
    public void setY(float y) {this.y = y;}}
