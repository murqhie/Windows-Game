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
    public View(){  }
    @Override
    public void settings() {
        //size(2000,1000);

      fullScreen();
    }
    @Override
    public void setup() {
        controller.setGameState("START");

    }
    public void draw(){
        controller.tick();

    }
    public void drawPlaying(){
        background(0);
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


    }
    public void drawGameOver(){
        textAlign(CENTER,CENTER);
        textSize(100);
        fill(100,10,10);
        text("GAME OVER",controller.getMainWindow().getPosition().getX() + (float) controller.getMainWindow().getWidth() / 2, (float) controller.getMainWindow().getPosition().getY() + controller.getMainWindow().getHeight() / 2);

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
