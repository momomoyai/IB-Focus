package ibfocus;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Sound {
    
    Clip clip;
    URL soundURL[] = new URL [30];
    
    public Sound(){
        ///game/res/music = Music, /game/res/sfx = SFX
        soundURL[0] = getClass().getResource("/ibfocus/res/DE.wav");
        soundURL[1] = getClass().getResource("/ibfocus/res/hakariNormalDE.wav");
        soundURL[2] = getClass().getResource("/ibfocus/res/luckDE.wav");
        soundURL[3] = getClass().getResource("/ibfocus/res/ImmersiveLuck.wav");
        
        
    }  
    public void set(int i){
        try{
            AudioInputStream is = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(is);
        }
        catch(Exception e){
            
        }
    }
    public void play(){
        clip.start();
    }
    public long pauseContinue(boolean pauseMusic, long musicClip){
        if (pauseMusic == false){
            long posisiPause = clip.getMicrosecondPosition();
            clip.stop();
            return posisiPause;
        }
        else {
            clip.setMicrosecondPosition(musicClip);
            clip.start();
            return 0;
        }
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
