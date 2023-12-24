package Controllers;
import Models.DataStructures.Vector;
import Models.Model;
import Models.Objects.*;
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
            view.drawStart();
            model.startNewGame(view.getScreenWidth(),view.getScreenHeight());
        }

        if(model.getState() == GameState.PLAYING) {
            model.setScore(getScore()+1);
            model.addEnemy();
            model.detectCollision();


            // Player
            getPlayer().setShootDirection(new Vector(view.getMousePosition().getX() - getPlayer().getX(), view.getMousePosition().getY() - getPlayer().getY()));
            model.getPlayer().move();
            model.getPlayer().attack(model.getProjectiles());
            if (model.getPlayer().isCollidingWithWindow()) {
                model.getPlayer().getsHit();
            }

            // Projectile
            model.getProjectiles().removeIf(Projectile::isCollided);

            for (Projectile projectile : model.getProjectiles()) {
                projectile.move();
            }

            // Window
            model.getMainWindow().move();

            //Enemies
            for (Enemy enemy : model.getEnemies()) {
                enemy.move();
                enemy.attack(model.getProjectiles());
                if(enemy.isDead()){
                    model.getWindows().remove(enemy.getWindow());}
            }
            model.getEnemies().removeIf(Enemy::isDead);

            model.checkGameOver();
            view.drawPlaying();
        }
        if(model.getState() == GameState.GAME_OVER) {
            view.drawGameOver();
        }

    }
    public void handleKeyPressed(KeyEvent event) {
        Player player = model.getPlayer();
        switch (model.getState()) {
            case START -> {
                if (event.getKeyCode() == ' ') {
                    model.setState(GameState.PLAYING);
                    model.startNewGame(view.getScreenWidth(),view.getScreenHeight());
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
                }
            }
        }
    }
    public void handleKeyReleased(KeyEvent event) {
        Player player = model.getPlayer();
        if (model.getState() == GameState.PLAYING) {
            if (Character.toLowerCase(event.getKey()) == 'w') {
                player.setKeyInputs(0, false);
            }
            if (Character.toLowerCase(event.getKey()) == 'a') {
                player.setKeyInputs(1, false);
            }
            if (Character.toLowerCase(event.getKey()) == 's') {
                player.setKeyInputs(2, false);
            }
            if (Character.toLowerCase(event.getKey()) == 'd') {
                player.setKeyInputs(3, false);
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
    public ArrayList<Enemy> getEnemies() {return model.getEnemies();}
    public ArrayList<Window> getEnemyWindows(){return model.getWindows();}
    public ArrayList<Projectile> getProjectiles(){return model.getProjectiles();}
    public Window getMainWindow(){return model.getMainWindow();}
    public int getScore(){return model.getScore();}
    public int getHighScore(){return model.getHighScore();}
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

}
