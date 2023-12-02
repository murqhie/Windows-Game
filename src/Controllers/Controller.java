package Controllers;
import Models.DataStructures.Vector;
import Models.Model;
import Models.Objects.Player;
import Models.Objects.Projectile;
import Models.Objects.Window;
import Views.IView;
import Models.DataStructures.GameState;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.util.ArrayList;

public class Controller implements IController {
    private Model model;
    private IView view;


    @Override
    public void tick() {
        if(model.getState() == GameState.START){
            int winWidth = 500;
            int winHeight = 500;
            model.getWindows().get(0).setWidth(winWidth);
            model.getWindows().get(0).setHeight(winHeight);
            model.getWindows().get(0).setPosition(new Vector((float) (view.getScreenWidth() /2-(winWidth/2)),(float)(view.getScreenHeight() /2-(winHeight/2))));

            model.startNewGame();
        }
        if(model.getState() == GameState.PLAYING){
            view.drawPlaying();
            getPlayer().setShootDirection(new Vector(view.getMousePosition().getX() - getPlayer().getX(), view.getMousePosition().getY() - getPlayer().getY()));
            model.getPlayer().move();
            model.getPlayer().shoot();

            for (Projectile projectile : model.getPlayer().getProjectiles()) {
                if(projectile.isCollidingWithWindow()){
                    projectile.getWindow().setPosition(projectile.getWindow().getPosition().add(projectile.getVelocity().unit().multiplicate(3)));
                }
            }
            model.getPlayer().getProjectiles().removeIf(Projectile::isCollidingWithWindow);

            for (Projectile projectile : model.getPlayer().getProjectiles()) {
                projectile.move();
            }
            model.checkGameOver();
        }
        if(model.getState() == GameState.GAME_OVER) {//model.drawGameOver()
        }
    }
    public void handleKeyPressed(KeyEvent event) {
        Player player = model.getPlayer();
        switch (model.getState()) {
            case START -> {
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
            case GAME_OVER -> {
                if (event.getKeyCode() == ' ') {
                    model.setState(GameState.START);

                }}

        }
    }
    public void handleKeyReleased(KeyEvent event) {
        Player player = model.getPlayer();
        switch (model.getState()) {
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
    public ArrayList<Window> getWindows(){return model.getWindows();}
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
        model.getWindows().get(0).setWidth(winWidth);
        model.getWindows().get(0).setHeight(winHeight);
        model.getWindows().get(0).setPosition(new Vector((float) (view.getScreenWidth() /2-(winWidth/2)),(float)(view.getScreenHeight() /2-(winHeight/2))));
    }

}
