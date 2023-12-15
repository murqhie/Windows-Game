package Models.Objects;

import Models.DataStructures.Vector;

import java.util.ArrayList;

public class Projectile {
    private Vector position;
    private Vector velocity;
    private int radius = 4;
    private int knockBack = 2;
    private boolean playerProjectile;
    private boolean collided = false;

    public Projectile(Vector position, Vector velocity, Boolean playerProjectile) {
        this.position = position;
        this.velocity = velocity;
        this.playerProjectile = playerProjectile;
    }
    public void getsOutOfWindow(Window mainWindow, ArrayList<Window> enemyWindows){

        boolean inMainWindow =
                this.position.getX() - radius >= mainWindow.getPosition().getX() &
                this.position.getY() - radius >= mainWindow.getPosition().getY() &
                this.position.getX() + radius <= mainWindow.getPosition().getX() + mainWindow.getWidth() &
                this.position.getY() + radius <= mainWindow.getPosition().getY() + mainWindow.getHeight();

        boolean wasInMainWindow =
                        this.position.getX() + radius >= mainWindow.getPosition().getX() &
                        this.position.getY() + radius>= mainWindow.getPosition().getY() &
                        this.position.getX() - radius<= mainWindow.getPosition().getX() + mainWindow.getWidth() &
                        this.position.getY() - radius <= mainWindow.getPosition().getY() + mainWindow.getHeight();

        for (Window enemyWindow : enemyWindows) {

            boolean inEnemyWindow =
                this.position.getX() - radius >= enemyWindow.getPosition().getX() &
                this.position.getY() - radius >= enemyWindow.getPosition().getY() &
                this.position.getX() + radius <= enemyWindow.getPosition().getX() + enemyWindow.getWidth() &
                this.position.getY() + radius <= enemyWindow.getPosition().getY() + enemyWindow.getHeight();

            if(!inMainWindow & !inEnemyWindow){
                this.collided = true;
                if (playerProjectile & wasInMainWindow) {mainWindow.setAcceleration(velocity.unit().multiplicate(knockBack));}
            }

        }

    }

    public void collidesWithEnemy(ArrayList<Enemy> enemies){
        for (Enemy enemy : enemies) {
            float distance = enemy.getPosition().add(this.position.multiplicate(-1)).norm();
            if (distance < (enemy.getRadius() + this.radius)){
                enemy.getsHit();
                this.collided = true;
            }
        }
    }

    public void collidesWithPlayer(Player player){
            float distance = player.getPosition().add(this.position.multiplicate(-1)).norm();
            if (distance < (player.getRadius() + this.radius)){
                player.getsHit();
                this.collided = true;
        }
    }


    public void move() {
        this.position = this.position.add(this.velocity);
    }
    public float getX(){return position.getX();}
    public float getY(){return position.getY();}
    public int getRadius() {return radius;}
    public Vector getVelocity() {return velocity;}
    public int getKnockBack() {return this.knockBack;}
    public boolean isPlayerProjectile() {return playerProjectile;}

    public boolean isCollided() {return collided;}
}
