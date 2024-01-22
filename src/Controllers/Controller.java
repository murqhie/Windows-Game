package Controllers;
import Models.DataStructures.Vector;
import Models.Model;
import Models.Objects.*;
import Views.IView;
import Models.DataStructures.GameState;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
import java.util.ArrayList;

/**
 * A controller implementation for an MVC concept. This controller creates and uses a {@link Model}.
 * It handles the user inputs and keeps track of the frame the view should draw next.
 */
public class Controller implements IController {
    private Model model;
    private IView view;

    /**
     * Calls all methods in model and view that should happen in every frame and ensures that way that necessary updates and operations are performed in sync for each frame.
     * tick() itself is called by draw() method which is called by Processing ~60 times per second.
     */
    @Override
    public void tick() {

        if(model.getState() == GameState.START){ performStart(); }
        if(model.getState() == GameState.PLAYING) { performPlaying(); }
        if(model.getState() == GameState.GAME_OVER) { view.drawGameOver(); }

    }
    private void performStart(){
        model.startNewGame(view.getScreenWidth(),view.getScreenHeight());
        view.drawStart();
        view.showStartButton();};
    private void performPlaying(){
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
    };

    /**
     * interprets the KeyEvent by Processing.
     * @param event Processing KeyEvent
     */
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
    /**
     * interprets the KeyEvent by Processing.
     * @param event Processing KeyEvent
     */
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
    /**
     * interprets the MouseEvent by Processing.
     * @param event Processing KeyEvent
     */
    @Override
    public void handleMousePressed(MouseEvent event) {
        if(event.getButton() ==37){
            model.getPlayer().setMouseInput(true);
        }
    }
    /**
     * interprets the MouseEvent by Processing.
     * @param event Processing KeyEvent
     */
    public void handleMouseReleased(MouseEvent event){
        if(event.getButton() ==37){
            model.getPlayer().setMouseInput(false);
        }
    }

    /**
     * Getter for Player.
     * Used by View to draw.
     * @return Player Object
     */
    public Player getPlayer(){
        return model.getPlayer();
    }
    /**
     * Getter for Enemies.
     * Used by View to draw.
     * @return Enemy ArrayList
     */
    public ArrayList<Enemy> getEnemies() {return model.getEnemies();}
    /**
     * Getter for Enemy Windows.
     * Used by View to draw.
     * @return Windows ArrayList
     */
    public ArrayList<Window> getEnemyWindows(){return model.getWindows();}
    /**
     * Getter for Projectiles.
     * Used by View to draw.
     * @return Projectile ArrayList
     */
    public ArrayList<Projectile> getProjectiles(){return model.getProjectiles();}
    /**
     * Getter for Main Window.
     * Used by View to draw.
     * @return Window Object
     */
    public Window getMainWindow(){return model.getMainWindow();}
    /**
     * Getter for score.
     * Used by View to draw.
     * @return int score
     */
    public int getScore(){return model.getScore();}
    /**
     * Getter for highscore.
     * Used by View to draw.
     * @return int highscore
     */
    public int getHighScore(){return model.getHighScore();}
    /**
     * Sets the model to use within this controller.
     * @param model the model to use
     */
    public void setModel(Model model) {this.model = model;}
    /**
     * Sets the view to use within this controller.
     * @param view the view to use
     */
    public void setView(IView view) {this.view = view;}
    /**
     * Setter for GameState.
     * @param state Game state
     */
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
