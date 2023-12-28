
package Views;
import processing.core.PApplet;
import processing.sound.*;
import java.io.File;
import java.util.HashMap;
import java.util.Random;


public class SoundLoader extends PApplet implements Runnable {
    private HashMap<String, SoundFile> sounds = new HashMap<>();
    private HashMap<String, Boolean> isPlaying = new HashMap<>();
    private File dir = new File("res/audio");
    String[] titleMusic = new String[]{"nyan"};
    String chosen = titleMusic[new Random().nextInt(titleMusic.length)];


    @Override
    public void run() {
        for (File file : dir.listFiles()) {
            if(file.getName().endsWith("wav")){loadAudio(file);}
        }
    }
    private void loadAudio(File file) {
        String name = file.getName().split("\\.")[0];
        sketchPath();
        sounds.put(name,new SoundFile(this, "audio/" + name + ".wav"));
        isPlaying.put(name,false);
    }
    public void playTitleMusic(){

        if(!isPlaying(chosen)){
            getSound(chosen).loop();
            setPlaying(chosen, true);

            setPlaying("error", false);
        }
    }

    public void playEndMusic(){

        if(!isPlaying("error")){
            getSound("error").play();
            setPlaying("error", true);

            for (String name : titleMusic) {
                getSound(name).stop();
                setPlaying(name, false);
            }chosen = titleMusic[new Random().nextInt(titleMusic.length)];
        }
    }
    public static SoundLoader initialize(){
        SoundLoader soundLoader = new SoundLoader();
        Thread thread = new Thread(soundLoader);
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return soundLoader;
    }
    public SoundFile getSound(String name) {return sounds.get(name);}
    public Boolean isPlaying(String name) {return isPlaying.get(name);}
    public void setPlaying(String name, boolean bool) {isPlaying.put(name, bool);}
}