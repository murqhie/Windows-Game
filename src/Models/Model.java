package Models;

import Models.DataStructures.GameState;
import Models.DataStructures.Timer;
import Models.DataStructures.Vector;
import Models.Objects.ICharacter;
import Models.Objects.Player;
import Models.Objects.Kamikaze;
import Models.Objects.Window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Model {
    private Player player;
    private GameState state;
    private Window mainWindow;
    private int screenWidth;
    private int screenHeight;
    private ArrayList<Window> enemyWindows = new ArrayList<>();
    private ArrayList<ICharacter> enemies;
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
        enemies.add(new Kamikaze(10, player, calcSpawnPosition()));
        addEnemyTimer.setRate(new Random().nextInt(50,200) );
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


    public void checkGameOver(){
        if(player.isDead()){state = GameState.GAME_OVER;}
    }
    public Player getPlayer() {return player;}
    public GameState getState() {return state;}
    public void setState(GameState state) {this.state = state;}
    public ArrayList<Window> getEnemyWindows(){return enemyWindows;}
    public Window getMainWindow() {return mainWindow;}
    public ArrayList<ICharacter> getEnemies() {return enemies;}
}
