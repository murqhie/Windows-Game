package Views;

import Controllers.IController;
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

    }

    public void draw(){
        controller.nextFrame();

    }

    public void setController(IController controller) {
        this.controller = controller;
    }
}
