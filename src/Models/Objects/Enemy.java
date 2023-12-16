package Models.Objects;
import Models.DataStructures.Vector;

public abstract class Enemy implements ICharacter{
    protected Vector position;
    protected Vector velocity;
    protected int radius;
    protected Player player;
    protected Window window;
    protected int Color = 200;
    protected int movingSpeed;
    protected boolean dead = false;
    protected Vector distance;

    public Enemy(Vector position, int radius,int movingSpeed, Player player, Window window) {
        this.position = position;
        this.radius = radius;
        this.movingSpeed = movingSpeed;
        this.player = player;
        this.window = window;
    }

    public void move() {
        distance = player.getPosition().add(this.position.multiplicate(-1));
        this.velocity = distance
                .unit()
                .multiplicate(movingSpeed);
        this.position = this.position.add(this.velocity);
    }
    public void getsHit() {
        this.dead = true;
    }
    public void collidesWithPlayer(Player player){
        float distance = player.getPosition().add(this.position.multiplicate(-1)).norm();
        if (distance < (player.getRadius() + this.radius)){
            player.getsHit();
        }
    }

    public int getColor() {return this.Color;}
    public Vector getPosition() {return position;}
    public float getX() {return this.position.getX();}
    public float getY() {return this.position.getY();}
    public int getRadius() {return this.radius;}
    public boolean isDead() {return dead;}

    public Window getWindow() {return window;}

    public boolean isInWindow(){
        return  (position.getX() < window.getPosition().getX() + window.getWidth()
                        & position.getX() > window.getPosition().getX())
                &
                (position.getY() < window.getPosition().getY() + window.getHeight()
                        & position.getY() > window.getPosition().getY());
    }


}
