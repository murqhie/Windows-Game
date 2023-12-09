package Models;

import Models.DataStructures.GameState;
import Models.Objects.Player;
import Models.Objects.Window;

import java.util.ArrayList;

public class Model {
    Player player;
    GameState state;
    Window mainWindow;
    ArrayList<Window> enemyWindows = new ArrayList<>();
    public void startNewGame(int screenWidth, int screenHeight){
        mainWindow  = new Window(800, 1200,screenWidth,screenHeight);
        player = new Player(30,mainWindow);}

    public void checkGameOver(){
        if(player.isCollidingWithWindow()){state = GameState.GAME_OVER;}
    }
    public Player getPlayer() {return player;}
    public GameState getState() {return state;}
    public void setState(GameState state) {this.state = state;}
    public ArrayList<Window> getEnemyWindows(){return enemyWindows;}
    public Window getMainWindow() {return mainWindow;}
}
