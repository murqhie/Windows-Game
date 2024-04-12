
package Views;
import processing.core.PApplet;
import processing.sound.*;
import java.io.File;
import java.util.HashMap;
import java.util.Random;

/**
 * Loads and provides all Sounds in the game.<br>
 * Part of the View.
 * <p>
 * Example:<br>
 * if(model.getState() == GameState.PLAYING) { soundLoader.playTitleMusic(); }<br>
 * if(model.getState() == GameState.GAME_OVER) { soundLoader.playEndMusic(); }<br>
 */

public class SoundLoader extends PApplet implements Runnable {
    private HashMap<String, SoundFile> sounds = new HashMap<>();
    private HashMap<String, Boolean> isPlaying = new HashMap<>();
    private File dir = new File("res/audio");
    private String[] titleMusic = new String[]{"Toxicity", "DuHast","MasterOfPuppets"};
    private String chosen = new Random().nextInt(100) == 0 ? "Nyan" : titleMusic[new Random().nextInt(titleMusic.length)];

    /**
     * run method for a thread.
     * Reads all soundfiles in res/audio.
     */
    @Override
    public void run() {
        for (File file : dir.listFiles()) {
            if(file.getName().endsWith("wav")){loadAudio(file);}
        }
    }
    private void loadAudio(File file) {

        String name = file.getName().split("\\.")[0];
        sketchPath();
        SoundFile sound = new SoundFile(this, "audio/" + name + ".wav");
        //sound.amp(0.0f);
        sounds.put(name,sound);
        isPlaying.put(name,false);
    }

    /**
     * Plays a random soundtrack from the titleMusic list and stops the error sound.
     * Has ~1% chance to play "Nyancat".
     */
    public void playTitleMusic(){
        if(!isPlaying(chosen)){
            getSound(chosen).loop();
            setPlaying(chosen, true);

            setPlaying("error", false);
        }
    }

    /**
     * Plays the Windows error sound and stops the music.
     * Rerolls the chosen music.
     */
    public void playEndMusic(){

        if(!isPlaying("error")){
            getSound("error").play();
            setPlaying("error", true);

            getSound("Nyan").stop();
            setPlaying("Nyan", false);
            for (String name : titleMusic) {
                getSound(name).stop();
                setPlaying(name, false);
            }
            chosen = new Random().nextInt(100) == 0 ? "Nyan" : titleMusic[new Random().nextInt(titleMusic.length)];
        }
    }

    /**
     * Getter for a specific sound.
     * @param name Sound name
     * @return SoundFile
     */

    public SoundFile getSound(String name) {return sounds.get(name);}

    /**
     * Gets an attribute "isPlaying" to prevent playing multiple sounds at once.
     * @param name Sound name
     * @return Boolean isPlaying
     */
    public Boolean isPlaying(String name) {return isPlaying.get(name);}

    /**
     * Sets an attribute "isPlaying" to prevent playing multiple sounds at once.
     * @param name Sound name
     * @param bool isPlaying
     */
    public void setPlaying(String name, boolean bool) {isPlaying.put(name, bool);}
}