
package Model.Troops;

import Model.Board;
import Model.TokenType;
import Model.Troop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Hero extends Troop{
    
    public Hero(String name, TokenType type, ArrayList<TokenType> targets, int health, int damagePerSecond, int range, int space, int unlockLevel, int cost, int level, Board board, ArrayList<String> images, Semaphore mutexTroops, Semaphore mutexDefenses) {
        super(name, type, targets, health, damagePerSecond, range, space, unlockLevel, cost, level, board, images, mutexTroops, mutexDefenses);
    }
    
    @Override
    protected void makeSound() throws IOException, LineUnavailableException, UnsupportedAudioFileException{
        String filePath = "D:\\NetBeans\\Projects\\War Of Inheritance\\Sounds\\Barbarian.wav";
        File f = new File(filePath);
        AudioInputStream audioIn = null;
        try{
            audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
        }
        catch (MalformedURLException e){
            System.out.println("Error while playing music");
        }
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(-25f);
        clip.start();
    }
}
