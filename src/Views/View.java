package Views;

import Controllers.IController;
import Models.DataStructures.Vector;
import Models.Objects.ICharacter;
import Models.Objects.Player;
import Models.Objects.Projectile;
import Models.Objects.Window;
import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.util.ArrayList;

public class View extends PApplet implements IView{
    IController controller;
    PImage desktopImage;
    public View(){  }
    @Override
    public void settings() {
        size(1920,1080);

      //fullScreen();
    }
    @Override
    public void setup() {
        controller.setGameState("START");
        desktopImage = loadImage("Desktop.png");
        desktopImage.resize(width,height);

    }
    public void draw(){
        controller.tick();
    }
    public void drawPlaying(){

        background(desktopImage);
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
        text(":(", 100, (float) (height * 0.3));
        textSize(50);
        text(String.format("Game Over! Press Enter to restart.\nYour score was %d and the highscore was %d.", controller.getScore()/10, controller.getHighScore()/10), 100, (float) (height * 0.45));

    }
    public void drawStart(){
        background(255);
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
