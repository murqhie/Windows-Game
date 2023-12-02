package Controllers;
import Models.Model;
import Models.Objects.Player;
import Views.IView;
import Models.DataStructures.GameState;

import processing.event.KeyEvent;

import static processing.core.PApplet.max;
import static processing.core.PApplet.min;

public class Controller implements IController {
    private Model model;
    private IView view;

    @Override
    public void nextFrame() {

        if(model.getState() == GameState.START){
            model.startNewGame();
            model.setState(GameState.PLAYING);
        }
        if(model.getState() == GameState.PLAYING){
            view.drawPlaying();
            model.getPlayer().move();
        }
    }
    public void handleKeyPressed(KeyEvent event) {
        Player player = model.getPlayer();
        switch (model.getState()) {
            case START, GAME_OVER -> {
                if (event.getKeyCode() == ' ') {
                    model.setState(GameState.PLAYING);
                    model.startNewGame();
                }
            }
            case PLAYING -> {

                if (event.getKey() == 'w') {
                    player.setAcceleration("UP");
                }
                if (event.getKey() == 'a') {
                    player.setAcceleration("LEFT");
                }
                if (event.getKey() == 's') {
                    player.setAcceleration("DOWN");;
                }
                if (event.getKey() == 'd') {
                    player.setAcceleration("RIGHT");;
                }
            }

        }
    }

    public void handleKeyReleased(KeyEvent event) {
        Player player = model.getPlayer();
        switch (model.getState()) {
            case START, GAME_OVER -> {
                if (event.getKeyCode() == ' ') {
                    model.setState(GameState.PLAYING);
                    model.startNewGame();
                }
            }
            case PLAYING -> {

                if (event.getKey() == 'w' | event.getKey() == 's') {
                    player.setAcceleration("YZERO");
                }
                if (event.getKey() == 'a' | event.getKey() == 'd') {
                    player.setAcceleration("XZERO");
                }
            }

        }
    }


    public Player getPlayer(){
        return model.getPlayer();
    }
    public void setModel(Model model) {this.model = model;}
    public void setView(IView view) {this.view = view;}
    public void setGameState(String state){
        if(state.equals("START")){
            model.setState(GameState.START);
        }
        if(state.equals("PLAYING")){
            model.setState(GameState.PLAYING);
        }
        if(state.equals("GAME_OVER")){
            model.setState(GameState.GAME_OVER);
        }
    }
    public void setWindow(int winWidth, int winHeight) {
        model.getWindow().setWidth(winWidth);
        model.getWindow().setHeight(winHeight);
    }

}
