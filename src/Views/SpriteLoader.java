package Views;
import processing.core.PImage;
import processing.core.PApplet;

import java.io.File;
import java.util.HashMap;


public class SpriteLoader extends PApplet implements Runnable {
    private HashMap<String, PImage> sprites = new HashMap<>();
    private File dir;
    int screenHeight;
    int screenWidth;
    int scaleFactor;

    public SpriteLoader(int screenWidth,int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.scaleFactor = screenHeight/360;
        this.dir = new File("images");
    }

    @Override
    public void run() {

        for (File file : dir.listFiles()) {
            loadSprite(file);
        }
    }

    public PImage getSprite(String name) {
        return sprites.get(name);
    }

    private void loadSprite(File file) {
        String name = file.getName().split("\\.")[0];
        int pixelWidth = parseInt(file.getName().split("\\.")[1]);
        int pixelHeight = parseInt(file.getName().split("\\.")[2]);;
        PImage image = loadImage(sketchPath() + "/images/" + file.getName());

        image.resize(pixelWidth *scaleFactor, pixelHeight *scaleFactor);
        sprites.put(name, image);
        System.out.println(name + " sprite successfully loaded");
    }
}