package Views;

import Controllers.IController;
import Models.Objects.Player;
import processing.core.PApplet;

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
        Player player = controller.getPlayer();
        rect(player.getX(),player.getY(),player.getHeight(),player.getWidth());
    }

    public void setController(IController controller) {
        this.controller = controller;
    }
}
