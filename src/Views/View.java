package Views;

import Controllers.IController;
import Models.DataStructures.Vector;
import Models.Objects.ICharacter;
import Models.Objects.Player;
import Models.Objects.Projectile;
import Models.Objects.Window;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.util.ArrayList;

public class View extends PApplet implements IView{
    IController controller;
    SpriteLoader sprites;

    @Override
    public void settings() {
        size(1920,1080);
      //fullScreen();
    }
    @Override
    public void setup() {

        sprites = new SpriteLoader(width,height);
        Thread thread = new Thread(sprites);
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        controller.setGameState("START");
    }
    public void draw(){
        controller.tick();
    }
    public void drawPlaying(){

        background(sprites.getSprite("Desktop"));
        drawWindow(controller.getMainWindow());
        for (Window window : controller.getEnemyWindows()) {
            drawWindow(window);
        }

        Player player = controller.getPlayer();
        drawProjectiles(controller.getProjectiles());
        drawCharacter(player);
        for (ICharacter enemy : controller.getEnemies()) {
            drawCharacter(enemy);
        }
        textAlign(LEFT,TOP);
        textSize(40);
        fill(100,180,180);
        text("Points: " + controller.getScore()/10 + " HighScore:  " + controller.getHighScore()/10,controller.getMainWindow().getPosition().getX()+2,controller.getMainWindow().getPosition().getY() +2);


    }
    public void drawGameOver(){
        background(30,130,220);
        textAlign(LEFT,TOP);
        textSize(150);
        fill(255);
        text(":(", 200, (float) (height * 0.2));
        textSize(50);
        text(String.format("""
                Your PC ran into a Problem\s
                ERROR: Game Over.

                Press [SPACE] to initiate system reboot.
                Your score was %d and the highscore was %d.""", controller.getScore()/10, controller.getHighScore()/10), 200, (float) (height * 0.35));
        image(sprites.getSprite("QR"), 200, (float) (height * 0.65));


    }
    public void drawStart(){
        background(sprites.getSprite("Desktop"));
        textAlign(CENTER,CENTER);
        textSize(100);
        fill(150,50,50);
        text("PRESS SPACE TO START", (float) width /2, (float) height /2);
    }

    @Override
    public Vector getMousePosition() {
        return new Vector(mouseX, mouseY);
    }

    private void drawProjectiles(ArrayList<Projectile> projectiles){
        for (Projectile projectile : projectiles) {
            fill(100,0,0);
            if(projectile.isPlayerProjectile()){fill(255);}
            circle(projectile.getX(),projectile.getY(),projectile.getRadius()*2);
        }
    }
    private void drawCharacter(ICharacter character){
        if (character.isInWindow()) {
            fill(character.getColor());
        }
        else{
        fill(80);}
        circle(character.getX(),character.getY(),character.getRadius()*2);
    }
    private void drawWindow(Window window){
        fill(window.getColor());
        rect(window.getPosition().getX(),window.getPosition().getY(),window.getWidth(), window.getHeight());
    }

    public void keyPressed(KeyEvent event){controller.handleKeyPressed(event);}
    public void keyReleased(KeyEvent event){controller.handleKeyReleased(event);}
    public void mousePressed(MouseEvent event){controller.handleMousePressed(event);}
    public void mouseReleased(MouseEvent event){controller.handleMouseReleased(event);}
    public void setController(IController controller) {this.controller = controller;}
    public int getScreenHeight(){return height;}
    public int getScreenWidth(){return width;}

}
