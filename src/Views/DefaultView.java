package Views;

import Controllers.IController;
import Models.DataStructures.Vector;
import Models.Objects.Player;
import Models.Objects.Projectile;
import processing.core.PApplet;

import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class DefaultView extends PApplet implements IView{
    IController controller;
    int winWidth;
    int winHeight;

    public DefaultView(int winWidth, int winHeight){
        this.winWidth = winWidth;
        this.winHeight = winHeight;
    }
    @Override
    public void settings() {
        setSize(winWidth,winHeight);
    }
    @Override
    public void setup() {
        controller.setGameState("START");
        controller.setWindow(winWidth, winHeight);

    }
    public void draw(){
        controller.tick();

    }
    public void drawPlaying(){
        background(0);
        Player player = controller.getPlayer();
        drawPlayer(player);
        drawProjectiles(player);


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

        circle(player.getX(),player.getY(),player.getRadius()*2);
    }

    public void keyPressed(KeyEvent event){controller.handleKeyPressed(event);}
    public void keyReleased(KeyEvent event){controller.handleKeyReleased(event);}
    public void mousePressed(MouseEvent event){controller.handleMousePressed(event);}
    public void mouseReleased(MouseEvent event){controller.handleMouseReleased(event);}
    public void setController(IController controller) {this.controller = controller;}
}
