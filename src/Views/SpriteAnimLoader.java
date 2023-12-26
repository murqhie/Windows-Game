package Views;
import processing.core.PImage;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class SpriteAnimLoader extends PApplet implements Runnable {
    private HashMap<String, ArrayList<PImage>> sprites = new HashMap<>();
    private File dir;
    int screenHeight;
    int screenWidth;
    float scaleFactor;

    public SpriteAnimLoader(int screenWidth,int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.scaleFactor = screenHeight/360f;
        this.dir = new File("images");
    }

    @Override
    public void run() {
        for (File file : dir.listFiles()) {
                loadSprite(file);
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
        PImage spriteSheet = loadImage(file.getName());



        int numberOfFrames = spriteSheet.width / (pixelWidth);
        for (int i = 0; i < numberOfFrames ; i++) {
            PImage sprite = spriteSheet.get(pixelWidth * i,0,pixelWidth,pixelHeight);
            sprite.resize((int) (pixelWidth/10 *scaleFactor), (int) (pixelHeight/10 *scaleFactor));
            sprites.get(name).add(sprite);
        }

    }
}