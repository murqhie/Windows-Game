package Models;

import Models.DataStructures.GameState;
import Models.DataStructures.Vector;
import Models.Objects.Player;
import Models.Objects.Window;

import java.util.ArrayList;

public class Model {
    Player player;
    GameState state;
    ArrayList<Window> windows = new ArrayList<>();
    public Model(){this.windows.add(new Window());}
    public void startNewGame(){
        player = new Player(30,this.windows.get(0));}
    public void checkGameOver(){
        if(player.isCollidingWithWindow()){state = GameState.GAME_OVER;}
    }
    public Player getPlayer() {return player;}
    public GameState getState() {return state;}
    public void setState(GameState state) {this.state = state;}
    public ArrayList<Window> getWindows(){return windows;}
}
