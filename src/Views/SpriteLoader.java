package Views;
import Models.DataStructures.Vector;
import processing.core.PImage;
import processing.core.PApplet;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Loads and provides all Sprites in the game.
 * Part of the View.
 */

public class SpriteLoader extends PApplet implements Runnable {
    private HashMap<String, ArrayList<PImage>> sprites = new HashMap<>();
    private File dir = new File("res/img");
    private float scaleFactor;

    /**
     * Constructor of SpriteLoader.
     * sets a scale factor for the images.
     * @param screenWidth screenWidth
     */
    public SpriteLoader(int screenWidth) {
        this.scaleFactor = screenWidth/640f;
    }

    /**
     * run method for a thread.
     * Reads all png files in res/img.
     */
    @Override
    public void run() {
        for (File file : dir.listFiles()) {
            if(file.getName().endsWith("png")){loadSprite(file);}
        }
    }
    /**
     * Getter for a specific sprite.
     * @param name Sprite name
     * @param frame frame number on sprite sheet
     * @return SoundFile
     */
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
            sprite.resize((int) (pixelWidth/10 *scaleFactor), (int) (pixelHeight/10 *scaleFactor));
            sprites.get(name).add(sprite);
        }

    }
}