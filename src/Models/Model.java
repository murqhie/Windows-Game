package Models;

import Models.DataStructures.*;
import Models.Objects.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * A model implementation for an MVC concept.<br>
 * Contains the game logic.
 * <p>
 * Example:<br>
 * Model model = new Model()<br>
 * model.startNewGame(100,100)<br>
 * model.getPlayer().getsHit()<br>
 * model.checkGameOver()<br>
 * model.getState()<br>
 */
public class Model {
    private Player player;
    private GameState state;
    private Window mainWindow;
    private int screenWidth,screenHeight,spawnRate, score = 0, highScore = 0;
    private ArrayList<Window> windows = new ArrayList<>();
    private ArrayList<Enemy> enemies;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private final Timer enemySpawnTimer = new Timer(0 , 0);

    /**
     * (Re-)sets all attributes that require an initial start value.
     * @param screenWidth width of the application window
     * @param screenHeight height of the application window
     */

    public void startNewGame(int screenWidth, int screenHeight){
        this.screenWidth = screenWidth; this.screenHeight = screenHeight;
        mainWindow  = new Window((int) (134 *(screenHeight/360f)), (int) (200 *(screenWidth/640f)),screenWidth,screenHeight);
        player = new Player(20,mainWindow);
        enemies = new ArrayList<>();
        projectiles = new ArrayList<>();
        windows = new ArrayList<>();
        spawnRate = 150;
        if(score >highScore){highScore = score;}
        score = 0;}

    /**
     * Adds one enemy of a random enemy type to the enemies ArrayList when the enemySpawnTimer is up
     */
    public void addEnemy(){
        boolean canSpawn = true;
        if(enemySpawnTimer.isUp()){
            int rand = new Random().nextInt(4);
            if(rand == 1){
                Vector virusPosition = calcSpawnPosition(new Vector(0,0),new Vector(this.screenWidth, this.screenHeight), mainWindow.getPosition(), new Vector(mainWindow.getPosition().getX()+ mainWindow.getWidth(),mainWindow.getPosition().getY()+ mainWindow.getHeight() ));
                Virus tempVirus = new Virus(virusPosition, player);
                windows.add(tempVirus.getWindow());
                enemies.add(tempVirus);}
            if(rand == 2){ enemies.add(new Bug(calcSpawnPosition(new Vector(-50,-50), new Vector(this.screenWidth +50, this.screenHeight+50), new Vector(0,0), new Vector(this.screenWidth, this.screenHeight)), player));}
            else{ for(Object enemy : enemies) {
                if (enemy instanceof AntiCursor) {
                    canSpawn = false;
                    break;
                } }
                if(canSpawn){enemies.add(new AntiCursor(calcSpawnPosition(new Vector(-50,-50), new Vector(this.screenWidth +50, this.screenHeight+50), new Vector(0,0), new Vector(this.screenWidth, this.screenHeight)), player));}}
            spawnRate = spawnRate > 50 ? spawnRate - 5 : spawnRate;
            enemySpawnTimer.setPeriod(spawnRate);
            enemySpawnTimer.reset();}
        enemySpawnTimer.tick();}

    private Vector calcSpawnPosition(Vector outerMin, Vector outerMax, Vector innerMin, Vector innerMax){

        int x = new Random().nextInt((int) outerMin.getX() , (int) outerMax.getX());
        int y = new Random().nextInt((int) outerMin.getY() , (int) outerMax.getY());

        if ((x > innerMin.getX() && x < innerMax.getX()) && (y > innerMin.getY() && y < innerMax.getY())) {return calcSpawnPosition(outerMin,outerMax,innerMin,innerMax);}
        return new Vector(x,y);}

    /**
     * detects collisions with projectile, player, enemy and windows
     */
    public void detectCollision(){
        for (Projectile projectile : projectiles) {

            if(Objects.equals(projectile.getParent(), "player")){
            if(projectile.collidesWithEnemy(enemies)){score += 1000;}}else{projectile.collidesWithPlayer(player);}
            projectile.getsOutOfWindow(mainWindow, windows);}

        for (Enemy enemy : enemies) {if(enemy.getClass() == AntiCursor.class){if(((AntiCursor) enemy).hasTrashBin()){enemy.collidesWithPlayer();}}else{enemy.collidesWithPlayer();}}}

    /**
     * checks if player is dead and switches game state to GAME_OVER when true
     */
    public void checkGameOver(){if(player.isDead()){state = GameState.GAME_OVER;}}

    /**
     * getter for player
     * @return Player Object
     */
    public Player getPlayer() {return player;}

    /**
     * getter for the game state
     * @return GameState
     */
    public GameState getState() {return state;}

    /**
     * setter for the game state
     * @param state GameState
     */
    public void setState(GameState state) {this.state = state;}

    /**
     * getter for all enemy windows
     * @return windows ArrayList
     */
    public ArrayList<Window> getWindows(){return windows;}

    /**
     * getter for main Window
     * @return main Window
     */
    public Window getMainWindow() {return mainWindow;}

    /**
     * getter for all enemies
     * @return enemies ArrayList
     */
    public ArrayList<Enemy> getEnemies() {return enemies;}

    /**
     * getter for all projectiles
     * @return projectiles ArrayList
     */
    public ArrayList<Projectile> getProjectiles() {return projectiles;}

    /**
     * getter for score
     * @return score
     */
    public int getScore() {return score;}

    /**
     * setter for score
     * @param score Score
     */
    public void setScore(int score) {this.score = score;}

    /**
     * getter for highscore
     * @return highscore
     */
    public int getHighScore() {return highScore;}
}
