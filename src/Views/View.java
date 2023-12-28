package Views;

import controlP5.*;
import Controllers.IController;
import Models.DataStructures.Vector;
import Models.Objects.*;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
import processing.sound.SoundFile;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static controlP5.ControlP5Constants.ACTION_RELEASE;

public class View extends PApplet implements IView{
    IController controller;
    SpriteAnimLoader sprites;
    SoundLoader soundLoader;
    Button startButton;
    int animationFrame = 0;
    int animationSpeed = 40;
    int shift;
    PFont dogica;
    PFont defaultFont;
    Vector cell1;
    Vector cell2;
    PImage loadingImage;
    int w, h;

    @Override
    public void settings() {
        loadingImage = loadImage("img/Loading.640.360.png");

        boolean fullscreen = false;

        if (fullscreen){
            fullScreen();
        }else {
            size(1920,1080);
        }
        loadingImage.resize(width, height);

    }
    @Override
    public void setup() {
        //TODO fix fullscreen

        background(loadingImage);

        soundLoader = new SoundLoader();
        Thread soundThread = new Thread(soundLoader);
        soundThread.start();

        sprites = new SpriteAnimLoader(width,height);
        Thread spriteThread = new Thread(sprites);
        spriteThread.start();


        Thread[] threads = new Thread[]{spriteThread, soundThread};

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        dogica = createFont("font/dogica.ttf", 128);
        defaultFont = createFont("font/default.ttf", 128);
        cell1 = new Vector((float) (width / 30),(float) (height / 8) - 55 );
        cell2 = new Vector( (float) (width / 30), (float) (height / 8*2) - 55);

        imageMode(CENTER);
        createButton();

        controller.setGameState("START");
    }
    public void draw(){
        controller.tick();
        animationFrame = frameCount%animationSpeed > animationSpeed/2 ? 1 :  0 ;
    }
    public void drawStart(){

        textAlign(CENTER,CENTER);
        background(sprites.getSprite("Desktop",0));
        textFont(dogica);
        textSize(10);
        fill(255);

        drawExplorer();
        drawBin();
    }
    public void drawPlaying(){
        soundLoader.playTitleMusic();

        background(sprites.getSprite("Desktop", 0));

        textSize(10);
        fill(255);
        textAlign(CENTER,CENTER);

        drawExplorer();
        drawBin();

        for (Window window : controller.getEnemyWindows()) drawWindow(window);
        drawWindow(controller.getMainWindow());
        drawScore();

        Player player = controller.getPlayer();
        drawProjectiles(controller.getProjectiles());
        drawCharacter(player);
        for (ICharacter enemy : controller.getEnemies()) {
            drawCharacter(enemy);
        }
    }
    public void drawGameOver(){
        soundLoader.playEndMusic();

        background(37, 150, 190);
        textAlign(LEFT,TOP);

        textFont(defaultFont);
        textSize(150);
        fill(255);
        text(":(", 200, (float) (height * 0.2));
        textSize(50);
        text(String.format("""
                Your PC ran into a problem and needs to restart.\s

                Press [SPACE] to initiate system reboot.
                Your score was %d and the highscore was %d.""", controller.getScore()/10, controller.getHighScore()/10), 200, (float) (height * 0.35));
        image(sprites.getSprite("QR",0), 296, (float) (height * 0.65) +96);

    }
    private void drawCharacter(ICharacter character){
        if (character.getClass().equals(Virus.class)) {
            drawVirus((Virus) character);
        } else if (character.getClass().equals(Bug.class)) {
            drawBug((Bug) character);
        } else if (character.getClass().equals(AntiCursor.class)) {
            drawAntiCursor((AntiCursor) character);
        } else if (character.getClass().equals(Player.class)) {
            drawPlayer((Player) character);
        }
    }
    private void drawPlayer(Player player){
        image(sprites.getSprite("Cursor",0), player.getX() + 4, player.getY());
    }
    private void drawVirus(Virus virus){
        image(sprites.getSprite("Virus",animationFrame), virus.getX(), virus.getY());
    }
    private void drawBug(Bug bug){pushMatrix();
        translate(bug.getX(), bug.getY());
        float angle = (float) (Math.acos((bug.getDistance().getX())/(bug.getDistance().norm())) + radians(45));
        if (bug.getY()>=controller.getPlayer().getY())
            angle=-radians(270)-angle;
        rotate(angle);
        image(sprites.getSprite("Bug",animationFrame),  0, 0);
        popMatrix();
    }
    private void drawAntiCursor(AntiCursor antiCursor){
        if(antiCursor.hasTrashBin()){
            image(sprites.getSprite("AntiCursor",1), antiCursor.getX()+ 4, antiCursor.getY());
        }else{
            image(sprites.getSprite("AntiCursor",0), antiCursor.getX()+ 4, antiCursor.getY());}
    }
    private void drawProjectiles(ArrayList<Projectile> projectiles){
        for (Projectile projectile : projectiles) {
            if(Objects.equals(projectile.getParent(), "player")){
                drawPlayerProjectile(projectile);
            }else if(Objects.equals(projectile.getParent(), "bug")){
               drawBugProjectile(projectile);
            }else if(Objects.equals(projectile.getParent(), "virus")){
                drawVirusProjectile(projectile);
            }
        }
    }
    private void drawPlayerProjectile(Projectile projectile) {
        fill(200, 200, 200);
        circle(projectile.getX(), projectile.getY(), projectile.getRadius() * 2);
        //image(sprites.getSprite("PlayerProjectile"), projectile.getX(), projectile.getY());
    }
    private void drawBugProjectile(Projectile projectile){ image(sprites.getSprite("Desktop",0).get(
                    (int) projectile.getX(),
                    (int) projectile.getY() + projectile.getRadius()*2 /3,
                    projectile.getRadius()*2,
                    projectile.getRadius()*2 /3),
            projectile.getX(),
            projectile.getY() + (float) (projectile.getRadius() * 2) /3 );

        image(sprites.getSprite("Desktop",0).get(
                        (int) projectile.getX(),
                        (int) projectile.getY() - projectile.getRadius()*2 /3,
                        projectile.getRadius()*2,
                        projectile.getRadius()*2 /3),
                projectile.getX(),
                projectile.getY() - (float) (projectile.getRadius() * 2) /3 );

        shift = animationFrame == 0 ? 10 :0;
        image(sprites.getSprite("Desktop",0).get(
                        (int) projectile.getX(),
                        (int) projectile.getY(),
                        projectile.getRadius()*2,
                        projectile.getRadius()*2 /3 +2),
                projectile.getX() + shift,
                projectile.getY());


        image(sprites.getSprite("HoleGlitch",animationFrame), projectile.getX(), projectile.getY());}
    private void drawVirusProjectile(Projectile projectile){
        image(sprites.getSprite("Projectile",new Random().nextInt(2)), projectile.getX(), projectile.getY());}
    private void drawWindow(Window window){
        String name = "VirusWindow";
        if(window == controller.getMainWindow()){name = "MainWindow";}
        rect(window.getPosition().getX(),window.getPosition().getY(),window.getWidth(), window.getHeight());
        image(sprites.getSprite(name,0),window.getPosition().getX()+window.getWidth()/2,window.getPosition().getY()+window.getHeight()/2);
    }
    public void drawExplorer(){
        image(sprites.getSprite("txt",0),cell1.getX(), cell1.getY());
        text("Internet\nExplorer", cell1.getX(), cell1.getY() + (float) sprites.getSprite("TrashBin", 0).height /2 + 10 );
    }
    public void drawBin(){
        boolean drawTrashBin = true;

        for (ICharacter enemy : controller.getEnemies()) {
            if(enemy.getClass().equals(AntiCursor.class) && ((AntiCursor) enemy).hasTrashBin()){
                drawTrashBin = false;
            };
        }
        if(drawTrashBin){
            image(sprites.getSprite("TrashBin", 0), (float) (width / 30), (float) (height / 8 * 2) - 55);
        }

        text("Recycle\nBin", cell2.getX(), cell2.getY() + (float) sprites.getSprite("TrashBin", 0).height /2 + 10);
    }
    public void drawScore(){
        textAlign(LEFT,TOP);
        textSize(18);
        fill(50);
        text("Points: " + controller.getScore()/10 + "\nHighScore: " + controller.getHighScore()/10,controller.getMainWindow().getPosition().getX()+20,controller.getMainWindow().getPosition().getY() +100);}
    public void createButton(){
        ControlP5 cp5 = new ControlP5(this);
        startButton = cp5.addButton("");
        startButton.setColorBackground( color( 255,255,255,1 ) );
        startButton.setColorForeground(color( 255,255,255,10 ) );
        startButton.setColorActive(color(0,0,0,10 ));
        startButton.setPosition((float) (cell1.getX() - width *0.05/2 - 10), (float) (cell1.getY() - height * 0.08 /2 - (height * 0.01)));
        startButton.setSize((int) (width *0.05) + 10, (int) ( (height * 0.08) + (height * 0.03)));
        startButton.addListenerFor(ACTION_RELEASE, new CallbackListener() {
            @Override
            public void controlEvent(CallbackEvent callbackEvent) {
                controller.setGameState("PLAYING");
                System.out.println("FASKBHJL");
                startButton.hide();
            }
        });}
    public void keyPressed(KeyEvent event){controller.handleKeyPressed(event);}
    public void keyReleased(KeyEvent event){controller.handleKeyReleased(event);}
    public void mousePressed(MouseEvent event){controller.handleMousePressed(event);}
    public void mouseReleased(MouseEvent event){controller.handleMouseReleased(event);}
    public void setController(IController controller) {this.controller = controller;}
    public int getScreenHeight(){return height;}
    public void showStartButton() {startButton.show();}
    public int getScreenWidth(){return width;}
    public Vector getMousePosition() {return new Vector(mouseX, mouseY);}
    public SoundLoader getSounds(){return soundLoader;}
}
