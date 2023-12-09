package Models;

import Models.DataStructures.GameState;
import Models.Objects.ICharacter;
import Models.Objects.Player;
import Models.Objects.Kamikaze;
import Models.Objects.Window;

import java.util.ArrayList;
import java.util.Arrays;

public class Model {
    private Player player;
    private GameState state;
    private Window mainWindow;
    private ArrayList<Window> enemyWindows = new ArrayList<>();
    private ArrayList<ICharacter> enemies;
    public void startNewGame(int screenWidth, int screenHeight){
        mainWindow  = new Window(800, 1200,screenWidth,screenHeight);
        player = new Player(30,mainWindow);
        enemies = new ArrayList<>(Arrays.asList(new Kamikaze(20, player), new Kamikaze(20,player)));
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
