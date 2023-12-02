package Controllers;
import Models.DataStructures.Vector;
import Models.Model;
import Models.Objects.Player;
import Models.Objects.Projectile;
import Views.IView;
import Models.DataStructures.GameState;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class Controller implements IController {
    private Model model;
    private IView view;


    @Override
    public void tick() {

        if(model.getState() == GameState.START){
            model.startNewGame();
            model.setState(GameState.PLAYING);
        }
        if(model.getState() == GameState.PLAYING){
            view.drawPlaying();
            model.getPlayer().move();
            model.getPlayer().shoot();

            for (Projectile projectile : model.getPlayer().getProjectiles()) {
                projectile.move();
            }
            model.checkGameOver();
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

                if (Character.toLowerCase(event.getKey()) == 'w') {
                    player.setKeyInputs(0,true);
                }
                if (Character.toLowerCase(event.getKey()) == 'a') {
                    player.setKeyInputs(1,true);
                }
                if (Character.toLowerCase(event.getKey()) == 's') {
                    player.setKeyInputs(2,true);
                }
                if (Character.toLowerCase(event.getKey()) == 'd') {
                    player.setKeyInputs(3,true);
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

                if (Character.toLowerCase(event.getKey()) == 'w') {
                    player.setKeyInputs(0,false);
                }
                if (Character.toLowerCase(event.getKey()) == 'a') {
                    player.setKeyInputs(1,false);
                }
                if (Character.toLowerCase(event.getKey()) == 's') {
                    player.setKeyInputs(2,false);
                }
                if (Character.toLowerCase(event.getKey()) == 'd') {
                    player.setKeyInputs(3,false);
                }
            }


        }
    }
    @Override
    public void handleMousePressed(MouseEvent event) {
        if(event.getButton() ==37){
            model.getPlayer().setMouseInput(true);
        }
    }
    public void handleMouseReleased(MouseEvent event){
        if(event.getButton() ==37){
            model.getPlayer().setMouseInput(false);
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
        model.getWindow().setPosition(new Vector(0,0));
    }

}
