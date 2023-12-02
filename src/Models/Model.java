package Models;

import Models.DataStructures.GameState;
import Models.DataStructures.Vector;
import Models.Objects.Player;
import Models.Objects.Window;

public class Model {
    Player player;
    GameState state;
    Window window;
    public Model(){this.window = new Window();}
    public void startNewGame(){player = new Player(30,this.window);}
    public void checkGameOver(){
        if(player.isCollidingWithWindow()){state = GameState.GAME_OVER;}
    }
    public Player getPlayer() {return player;}
    public GameState getState() {return state;}
    public void setState(GameState state) {this.state = state;}
    public Window getWindow(){return window;}
}
