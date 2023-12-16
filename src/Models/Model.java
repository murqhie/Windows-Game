package Models;

import Models.DataStructures.GameState;
import Models.DataStructures.Timer;
import Models.DataStructures.Vector;
import Models.Objects.*;

import java.util.ArrayList;
import java.util.Random;

public class Model {
    private Player player;
    private GameState state;
    private Window mainWindow;
    private int screenWidth;
    private int screenHeight;
    private ArrayList<Window> windows = new ArrayList<>();
    private ArrayList<Enemy> enemies;
    private Timer addEnemyTimer = new Timer(0 , 0);

    public void startNewGame(int screenWidth, int screenHeight){
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        mainWindow  = new Window(800, 1200,screenWidth,screenHeight);
        player = new Player(30,mainWindow);
        enemies = new ArrayList<>();
    }
    public void addEnemy(){
        if(addEnemyTimer.isUp()){

        //enemies.add(new Kamikaze(calcSpawnPosition(), player, mainWindow));
        //enemies.add(new Stalker(calcSpawnPosition(), player, mainWindow));
        Tower tempTower = new Tower(new Vector(350,350), player, mainWindow);
        windows.add(tempTower.getWindow());
        enemies.add(tempTower);

        addEnemyTimer.setRate(new Random().nextInt(80,100) );
        addEnemyTimer.reset();
        }
        addEnemyTimer.tick();

    }

    private Vector calcSpawnPosition(){
        int x = new Random().nextInt(-50,screenWidth+50);
        int y = new Random().nextInt(-50,screenHeight+50);

        if ((x < screenWidth & x > 0) &
                (y < screenHeight & y > 0)) {
            return calcSpawnPosition();
        }
        return new Vector(x,y);
    }
    public void detectCollision(){
        for (Projectile projectile : player.getProjectiles()) {
            projectile.collidesWithEnemy(enemies);
            projectile.getsOutOfWindow(mainWindow, windows);
        }
        for (Enemy enemy : enemies) {
            if(enemy.getProjectiles() != null){
            for (Projectile projectile : enemy.getProjectiles()) {
                projectile.collidesWithPlayer(player);
                projectile.getsOutOfWindow(mainWindow, windows);
                }
            }
            enemy.collidesWithPlayer(player);
        }
    }

    public void checkGameOver(){
        if(player.isDead()){state = GameState.GAME_OVER;}
    }
    public Player getPlayer() {return player;}
    public GameState getState() {return state;}
    public void setState(GameState state) {this.state = state;}
    public ArrayList<Window> getWindows(){return windows;}
    public Window getMainWindow() {return mainWindow;}
    public ArrayList<Enemy> getEnemies() {return enemies;}
}
