package Views;

import Controllers.IController;
import Models.DataStructures.Vector;
import Models.Objects.Player;
import Models.Objects.Projectile;
import Models.Objects.Window;
import processing.core.PApplet;

import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class DefaultView extends PApplet implements IView{
    IController controller;
    public DefaultView(){  }
    @Override
    public void settings() {
        size(1000,1000);
        // fullScreen();
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
        for (Window window : controller.getWindows()) {
            fill(window.getColor());
            rect(window.getPosition().getX(),window.getPosition().getY(),window.getWidth(), window.getHeight());
        }
        Player player = controller.getPlayer();


        drawProjectiles(player);
        drawPlayer(player);


    }

    @Override
    public Vector getMousePosition() {
        return new Vector(mouseX, mouseY);
    }

    private void drawProjectiles(Player player){
        for (Projectile projectile : player.getProjectiles()) {
            circle(projectile.getX(),projectile.getY(),projectile.getRadius()*2);
        }
    }
    private void drawPlayer(Player player){
        fill(player.getColor());
        circle(player.getX(),player.getY(),player.getRadius()*2);
    }

    public void keyPressed(KeyEvent event){controller.handleKeyPressed(event);}
    public void keyReleased(KeyEvent event){controller.handleKeyReleased(event);}
    public void mousePressed(MouseEvent event){controller.handleMousePressed(event);}
    public void mouseReleased(MouseEvent event){controller.handleMouseReleased(event);}
    public void setController(IController controller) {this.controller = controller;}
    public int getScreenHeight(){return height;}
    public int getScreenWidth(){return width;}

}
