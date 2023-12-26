package Views;

import Controllers.IController;
import Models.DataStructures.Vector;
import Models.Objects.*;
import processing.core.PApplet;
import processing.core.PFont;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.util.ArrayList;

public class View extends PApplet implements IView{
    IController controller;
    SpriteAnimLoader sprites;
    int angle =1;
    int animationFrame = 0;
    int animationSpeed = 40;
    int shift;
    PFont dogica;
    PFont defaultFont;


    @Override
    public void settings() {
      size(1920,1080);
    //fullScreen();
    }
    @Override
    public void setup() {
     
        dogica = createFont("dogica.ttf", 128);
        defaultFont = createFont("default.ttf", 128);

        imageMode(CENTER);
        sprites = new SpriteAnimLoader(width,height);
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
        animationFrame = frameCount%animationSpeed > animationSpeed/2 ? 1 :  0 ;
    }
    public void drawPlaying(){

        background(sprites.getSprite("Desktop", 0));

        textSize(10);
        fill(255);

        image(sprites.getSprite("TrashBin",0), (float) (width / 30), (float) (height / 8) - 55 );
        text("CLICKME.EXE", (float) (width / 30), (float) (height / 8) );

        image(sprites.getSprite("TrashBin", 0), (float) (width / 30), (float) (height / 8 * 2) - 55);
        text("Trash Bin", (float) (width / 30), (float) (height / 8 * 2));

        for (Window window : controller.getEnemyWindows()) drawWindow(window);
        drawWindow(controller.getMainWindow());

        Player player = controller.getPlayer();
        drawProjectiles(controller.getProjectiles());
        drawCharacter(player);
        for (ICharacter enemy : controller.getEnemies()) {
            drawCharacter(enemy);
        }
        textAlign(LEFT,TOP);
        textSize(20);
        fill(20);
        text("Points: " + controller.getScore()/10 + " HighScore:  " + controller.getHighScore()/10,controller.getMainWindow().getPosition().getX()+30,controller.getMainWindow().getPosition().getY() +40);


    }
    public void drawGameOver(){
        background(30,130,220);
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
    public void drawStart(){
        background(sprites.getSprite("Desktop",0));
        textFont(dogica);
        textSize(10);
        fill(255);


        image(sprites.getSprite("TrashBin",0), (float) (width / 30), (float) (height / 8) - 55 );
        text("CLICKME.EXE", (float) (width / 30), (float) (height / 8) );

        image(sprites.getSprite("TrashBin",0), (float) (width / 30), (float) (height / 8*2) - 55 );
        text("Trash Bin", (float) (width / 30), (float) (height / 8*2) );

        textAlign(CENTER,CENTER);
        textSize(50);
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
            if(projectile.isPlayerProjectile()){
                circle(projectile.getX(),projectile.getY(),projectile.getRadius()*2);
                //image(sprites.getSprite("PlayerProjectile"), projectile.getX(), projectile.getY());
            }else{
                image(sprites.getSprite("Desktop",0).get(
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


                image(sprites.getSprite("HoleGlitch",animationFrame), projectile.getX(), projectile.getY());

                //image(sprites.getSprite("Projectile",animationFrame), projectile.getX(), projectile.getY());
            }

        }
    }
    private void drawCharacter(ICharacter character){
        if (character.getClass().equals(Virus.class)) {
            image(sprites.getSprite("Virus",animationFrame), character.getX(), character.getY());
        } else if (character.getClass().equals(Bug.class)) {
            pushMatrix();
            translate(character.getX(), character.getY());
            float angle = (float) (Math.acos((1*((Bug) character).getDistance().getX())/(((Bug) character).getDistance().norm())) + radians(45));
            if (character.getY()>=controller.getPlayer().getY())
                angle=-radians(270)-angle;
            rotate(angle);

            image(sprites.getSprite("Bug",animationFrame),  0, 0);
            popMatrix();
        } else if (character.getClass().equals(AntiCursor.class)) {
            if(((AntiCursor) character).hasTrashBin()){
                image(sprites.getSprite("AntiCursor",1), character.getX()+ 4, character.getY());
            }else{
                image(sprites.getSprite("AntiCursor",0), character.getX()+ 4, character.getY());}
        } else if (character.getClass().equals(Player.class)) {
            image(sprites.getSprite("Cursor",0), character.getX() + 4, character.getY());
        }

    }
    private void drawWindow(Window window){
        String name = "VirusWindow";
        if(window == controller.getMainWindow()){name = "MainWindow";}
        rect(window.getPosition().getX(),window.getPosition().getY(),window.getWidth(), window.getHeight());
        image(sprites.getSprite(name,0),window.getPosition().getX()+window.getWidth()/2,window.getPosition().getY()+window.getHeight()/2);
    }

    public void keyPressed(KeyEvent event){controller.handleKeyPressed(event);}
    public void keyReleased(KeyEvent event){controller.handleKeyReleased(event);}
    public void mousePressed(MouseEvent event){controller.handleMousePressed(event);}
    public void mouseReleased(MouseEvent event){controller.handleMouseReleased(event);}
    public void setController(IController controller) {this.controller = controller;}
    public int getScreenHeight(){return height;}
    public int getScreenWidth(){return width;}

}
