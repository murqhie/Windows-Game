package Models;

import Models.DataStructures.GameState;
import Models.DataStructures.Timer;
import Models.DataStructures.Vector;
import Models.Objects.*;

import java.util.ArrayList;
import java.util.Random;

public class Model {
    private Player player;
    private GameState state;
    private Window mainWindow;
    private int screenWidth,screenHeight,spawnRate, score = 0, highScore = 0;
    private ArrayList<Window> windows = new ArrayList<>();
    private ArrayList<Enemy> enemies;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private Timer addEnemyTimer = new Timer(0 , 0);



    public void startNewGame(int screenWidth, int screenHeight){
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        mainWindow  = new Window(402, 600,screenWidth,screenHeight);
        player = new Player(20,mainWindow);
        enemies = new ArrayList<>();
        projectiles = new ArrayList<>();
        windows = new ArrayList<>();
        spawnRate = 150;
        if(score >highScore){highScore = score;}
        score = 0;

    }

    public void addEnemy(){
        if(addEnemyTimer.isUp()){
            int rand = new Random().nextInt(4);
            if(rand == 0){
                Vector virusPosition = calcSpawnPosition(new Vector(0,0),new Vector(this.screenWidth, this.screenHeight), mainWindow.getPosition(), new Vector(mainWindow.getPosition().getX()+ mainWindow.getWidth(),mainWindow.getPosition().getY()+ mainWindow.getHeight() ));
                Virus tempVirus = new Virus(virusPosition, player, new Window(402, 600, new Vector( (virusPosition.getX()- 300), (virusPosition.getY()-201))));
                windows.add(tempVirus.getWindow());
                enemies.add(tempVirus);}
            if(rand == 1){enemies.add(new AntiCursor(calcSpawnPosition(new Vector(-50,-50), new Vector(this.screenWidth +50, this.screenHeight+50), new Vector(0,0), new Vector(this.screenWidth, this.screenHeight)), player, mainWindow));}
            else{enemies.add(new Bug(calcSpawnPosition(new Vector(-50,-50), new Vector(this.screenWidth +50, this.screenHeight+50), new Vector(0,0), new Vector(this.screenWidth, this.screenHeight)), player, mainWindow));}

            spawnRate = spawnRate > 50 ? spawnRate - 5 : spawnRate;

            addEnemyTimer.setRate(spawnRate);
            addEnemyTimer.reset();}
        addEnemyTimer.tick();}
    private Vector calcSpawnPosition(Vector outerMin, Vector outerMax, Vector innerMin, Vector innerMax){

        int x = new Random().nextInt((int) outerMin.getX() , (int) outerMax.getX());
        int y = new Random().nextInt((int) outerMin.getY() , (int) outerMax.getY());

        if ((x > innerMin.getX() && x < innerMax.getX()) && (y > innerMin.getY() && y < innerMax.getY())) {return calcSpawnPosition(outerMin,outerMax,innerMin,innerMax);}
        return new Vector(x,y);}
    public void detectCollision(){
        for (Projectile projectile : projectiles) {

            if(projectile.isPlayerProjectile()){
            if(projectile.collidesWithEnemy(enemies)){
                score += 1000;}
            }else{projectile.collidesWithPlayer(player);}
            projectile.getsOutOfWindow(mainWindow, windows);}

        for (Enemy enemy : enemies) {
            enemy.collidesWithPlayer(player);}}

    public void checkGameOver(){if(player.isDead()){state = GameState.GAME_OVER;}}
    public Player getPlayer() {return player;}
    public GameState getState() {return state;}
    public void setState(GameState state) {this.state = state;}
    public ArrayList<Window> getWindows(){return windows;}
    public Window getMainWindow() {return mainWindow;}
    public ArrayList<Enemy> getEnemies() {return enemies;}
    public ArrayList<Projectile> getProjectiles() {return projectiles;}
    public int getScore() {return score;}
    public void setScore(int score) {this.score = score;}
    public int getHighScore() {return highScore;}
}
