package Models.Objects;

import Models.DataStructures.Timer;
import Models.DataStructures.Vector;

import java.util.ArrayList;
import java.util.Collection;

public class Tower extends Enemy{
    private Window window;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private Timer shootTimer = new Timer(50, 0); // (Delay, Timer)
    private int shootingSpeed = 5;
    public Tower(Vector position, Player player, Window mainWindow) {
        super(position, 50, 0, player, mainWindow);
        window = new Window(500,500, mainWindow.getScreenWidth(), mainWindow.getScreenHeight(), new Vector(this.position.getX()-500,this.position.getY()-500));
    }
    public void attack() {
        if(shootTimer.isUp()){
            projectiles.add(new Projectile(this.position,distance.unit().multiplicate(shootingSpeed),this.window, false));
            shootTimer.reset();
        }
        shootTimer.tick();
    }
    public ArrayList<Projectile> getProjectiles() {return projectiles;}
    public Window getWindow() {return window;}
}
