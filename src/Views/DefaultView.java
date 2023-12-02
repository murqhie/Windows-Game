package Views;

import Controllers.IController;
import Models.Objects.Player;
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
        controller.nextFrame();

    }
    public void drawPlaying(){
        background(0);
        Player player = controller.getPlayer();
        circle(player.getX(),player.getY(),player.getRadius()*2);
    }
    public void keyPressed(KeyEvent event){controller.handleKeyPressed(event);}
    public void keyReleased(KeyEvent event){controller.handleKeyReleased(event);}
    public void mousePressed(MouseEvent event){controller.handleMouse(event);}
    public void setController(IController controller) {this.controller = controller;}
}
