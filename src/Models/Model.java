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
    public void startNewGame(){
        player = new Player(30,30,new Vector(window.getWidth()/2-15, window.getHeight()/2-15));
    }
    public Player getPlayer() {return player;}
    public GameState getState() {return state;}
    public void setState(GameState state) {this.state = state;}
    public Window getWindow(){return window;}
}
