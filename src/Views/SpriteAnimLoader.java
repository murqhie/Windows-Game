package Views;
import Models.DataStructures.Vector;
import processing.core.PImage;
import processing.core.PApplet;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class SpriteAnimLoader extends PApplet implements Runnable {
    private HashMap<String, ArrayList<PImage>> sprites = new HashMap<>();
    private File dir;
    int screenHeight;
    int screenWidth;
    Vector scaleFactor;

    public SpriteAnimLoader(int screenWidth,int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.scaleFactor = new Models.DataStructures.Vector(screenWidth/640f,screenHeight/360f);
        this.dir = new File("res/img");
    }

    @Override
    public void run() {
        for (File file : dir.listFiles()) {
            if(file.getName().endsWith("png")){loadSprite(file);}
        }
    }
    public PImage getSprite(String name, int frame) {
        return sprites.get(name).get(frame);
    }
    private void loadSprite(File file) {
        String name = file.getName().split("\\.")[0];
        int pixelWidth = parseInt(file.getName().split("\\.")[1]) *10;
        int pixelHeight = parseInt(file.getName().split("\\.")[2]) * 10;


        sprites.put(name,new ArrayList<PImage>());

        sketchPath();
        PImage spriteSheet = loadImage("img/" + file.getName());



        int numberOfFrames = spriteSheet.width / (pixelWidth);
        for (int i = 0; i < numberOfFrames ; i++) {
            PImage sprite = spriteSheet.get(pixelWidth * i,0,pixelWidth,pixelHeight);
            sprite.resize((int) (pixelWidth/10 *scaleFactor.getX()), (int) (pixelHeight/10 *scaleFactor.getY()));
            sprites.get(name).add(sprite);
        }

    }
}